package com.example.ecommerce_backend.service.implementations;

import static com.example.ecommerce_backend.util.constant.OrderStatus.*;

import com.example.ecommerce_backend.exception.InvalidOrderException;
import com.example.ecommerce_backend.exception.InvalidQuantityException;
import com.example.ecommerce_backend.exception.InvalidStateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.model.dto.orderentity.OrderEntityConfirmDto;
import com.example.ecommerce_backend.model.dto.orderentity.OrderEntityCreateDto;
import com.example.ecommerce_backend.model.dto.orderentity.OrderItemEntityCreateDto;
import com.example.ecommerce_backend.model.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.model.dto.paypaldto.*;
import com.example.ecommerce_backend.model.entity.ItemEntity;
import com.example.ecommerce_backend.model.entity.OrderEntity;
import com.example.ecommerce_backend.model.entity.OrderItemEntity;
import com.example.ecommerce_backend.model.entity.UserEntity;
import com.example.ecommerce_backend.repository.OrderEntityRepository;
import com.example.ecommerce_backend.service.interfaces.*;
import com.example.ecommerce_backend.service.interfaces.PaypalClientInterface;
import com.example.ecommerce_backend.util.constant.OrderCreateType;
import com.example.ecommerce_backend.util.constant.OrderPaypalStatus;
import com.example.ecommerce_backend.util.constant.OrderStatus;
import com.example.ecommerce_backend.util.constant.PaypalOrderIntent;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderServiceInterface {

  private final OrderEntityRepository orderEntityRepository;

  private final PaypalClientInterface paypalClientInterface;

  private final ItemServiceInterface itemServiceInterface;

  private final CartServiceInterface cartServiceInterface;

  private final UserServiceInterface userServiceInterface;

  private final EmailServiceInterface emailServiceInterface;

  @Override
  public List<OrderEntity> confirmOrders(List<OrderEntityConfirmDto> orderEntityConfirmDtoList) {
    if (orderEntityConfirmDtoList.stream()
        .anyMatch(
            orderEntityConfirmDto ->
                orderEntityConfirmDto.getStatus() != TRACKING
                    && orderEntityConfirmDto.getStatus() != CANCELLED)) {
      throw new InvalidStateException("Invalid order status, admin must tracking or cancel orders");
    }

    List<OrderEntity> orderEntityList =
        orderEntityConfirmDtoList.stream()
            .map(
                orderEntityConfirmDto -> {
                  OrderEntity orderEntity =
                      orderEntityRepository
                          .findById(orderEntityConfirmDto.getOrderId())
                          .orElseThrow(
                              () ->
                                  new ResourceNotFoundException(
                                      String.format(
                                          "Order id: %d not found",
                                          orderEntityConfirmDto.getOrderId())));

                  if (orderEntity.getStatus() == PAID) {
                    orderEntity.setStatus(orderEntityConfirmDto.getStatus());
                  } else {
                    throw new InvalidStateException(
                        String.format(
                            "Order status is not PAID, actual status: %s",
                            orderEntity.getStatus().name()));
                  }
                  return orderEntity;
                })
            .toList();

    List<OrderEntity> savedOrderEntityList =
        orderEntityRepository.findAllByIdIn(
            orderEntityRepository.saveAll(orderEntityList).stream()
                .map(OrderEntity::getId)
                .toList());
    emailServiceInterface.sendApprovedOrderEmail(savedOrderEntityList);

    return savedOrderEntityList;
  }

  @Override
  public Page<OrderEntity> findAllByStatus(OrderStatus orderStatus, Pageable pageable) {
    return orderEntityRepository.findAllByStatus(orderStatus, pageable);
  }

  @Override
  public OrderEntity createOrder(@NotNull OrderEntityCreateDto orderEntityCreateDto) {
    AtomicReference<Double> grandTotal = new AtomicReference<>((double) 0);
    AtomicReference<Double> discountPrice = new AtomicReference<>((double) 0);
    OrderEntity savedOrderEntity;

    if (orderEntityCreateDto.getOrderCreateType() == OrderCreateType.WITH_USERID) {
      savedOrderEntity =
          buildOrderWithUserIdAndSaveToLocalDB(orderEntityCreateDto, grandTotal, discountPrice);
    } else if (orderEntityCreateDto.getOrderCreateType() == OrderCreateType.WITH_EMAIL) {
      savedOrderEntity =
          buildOrderWithEmailAndSaveToLocalDB(orderEntityCreateDto, grandTotal, discountPrice);
    } else {
      throw new InvalidOrderException("Invalid order create type");
    }

    OrderDto orderRequest = buildOrderDto(savedOrderEntity, discountPrice);
    OrderResponseDto orderResponseDto = sendCreateOrderRequestToPaypal(orderRequest);

    if (orderResponseDto.getStatus() == OrderPaypalStatus.CREATED) {
      savedOrderEntity.setStatus(CREATED);
    } else {
      log.error("Order creation failed with paypal id: {}", orderResponseDto.getId());
      savedOrderEntity.setStatus(ERRORED);
    }

    savedOrderEntity.setPaypalOrderId(orderResponseDto.getId());
    return orderEntityRepository.save(savedOrderEntity);
  }

  @Override
  public OrderEntity payOrder(UserPayOrderDto userPayOrderDto) {
    OrderResponseDto orderResponseDto = sendCaptureOrderRequestToPaypal(userPayOrderDto);

    Optional<OrderEntity> orderEntity =
        orderEntityRepository.findByPaypalOrderId(orderResponseDto.getId());
    if (orderEntity.isEmpty()) {
      throw new ResourceNotFoundException(
          "Can not find order with paypal id: " + orderResponseDto.getId());
    }

    if (orderResponseDto.getStatus() == OrderPaypalStatus.COMPLETED) {
      orderEntity.get().setStatus(PAID);
    } else {
      log.error("Order creation failed with paypal id: {}", orderResponseDto.getId());
      orderEntity.get().setStatus(ERRORED);
    }

    orderEntity.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    orderEntityRepository.save(orderEntity.get());
    emailServiceInterface.sendReceiptEmail(orderEntity.get());

    return orderEntity.get();
  }

  @NotNull
  private OrderEntity buildOrderWithEmailAndSaveToLocalDB(
      @NotNull OrderEntityCreateDto orderEntityCreateDto,
      AtomicReference<Double> grandTotal,
      AtomicReference<Double> discountPrice) {
    OrderEntity newOrderEntity =
        OrderEntity.builder()
            .email(orderEntityCreateDto.getEmail())
            .status(CREATED)
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .build();

    List<OrderItemEntity> orderItemEntityList =
        buildListOfOrderItemEntity(
            orderEntityCreateDto.getItemList(), grandTotal, discountPrice, newOrderEntity);

    newOrderEntity.setGrandTotal(String.valueOf(grandTotal.get()));
    newOrderEntity.setOrderItemEntityList(orderItemEntityList);
    return orderEntityRepository.save(newOrderEntity);
  }

  @NotNull
  private OrderEntity buildOrderWithUserIdAndSaveToLocalDB(
      @NotNull OrderEntityCreateDto orderEntityCreateDto,
      AtomicReference<Double> grandTotal,
      AtomicReference<Double> discountPrice) {
    UserEntity userEntity = userServiceInterface.findUserById(orderEntityCreateDto.getUserId());
    OrderEntity newOrderEntity =
        OrderEntity.builder()
            .userEntity(userEntity)
            .email(userEntity.getEmail())
            .status(CREATED)
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .build();

    List<OrderItemEntity> orderItemEntityList =
        buildListOfOrderItemEntity(
            orderEntityCreateDto.getItemList(), grandTotal, discountPrice, newOrderEntity);

    newOrderEntity.setGrandTotal(String.valueOf(grandTotal.get()));
    newOrderEntity.setOrderItemEntityList(orderItemEntityList);
    return orderEntityRepository.save(newOrderEntity);
  }

  @SuppressWarnings("java:S6204")
  private List<OrderItemEntity> buildListOfOrderItemEntity(
      @NotNull List<OrderItemEntityCreateDto> orderItemEntityCreateDtoList,
      AtomicReference<Double> grandTotal,
      AtomicReference<Double> discountPrice,
      OrderEntity newOrderEntity) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    return orderItemEntityCreateDtoList.stream()
        .map(
            (orderItemCreateDto -> {
              ItemEntity itemEntity;
              if (orderItemCreateDto.getItemEntityId() != null) {
                itemEntity =
                    itemServiceInterface.findItemEntityById(orderItemCreateDto.getItemEntityId());
              } else if (orderItemCreateDto.getProductId() != null
                  && orderItemCreateDto.getVariationEntityIdSet() != null) {
                itemEntity =
                    itemServiceInterface.findItemEntityUsingVariationEntityIdSet(
                        orderItemCreateDto.getProductId(),
                        orderItemCreateDto.getVariationEntityIdSet());
              } else {
                throw new InvalidOrderException(
                    "ItemEntityId or ProductId and VariationEntityIdSet must be provided");
              }

              if (itemEntity.isDisabled()) {
                throw new InvalidStateException("The requested item is disabled");
              } else if (orderItemCreateDto.getQuantity()
                  > Integer.parseInt(itemEntity.getStock())) {
                throw new InvalidQuantityException(
                    "The requested item stock is smaller than the requested quantity");
              }

              if (orderItemCreateDto.getCartItemId() != null) {
                cartServiceInterface.delete(orderItemCreateDto.getCartItemId());
              }

              double discountPercent =
                  Double.parseDouble(
                          itemEntity.getProductEntity().getDiscountEntity().getDiscountPercent())
                      / 100;
              double totalPrice =
                  (Integer.parseInt(itemEntity.getPrice()) * orderItemCreateDto.getQuantity())
                      * (1 - discountPercent);
              grandTotal.updateAndGet(v -> v + totalPrice);
              discountPrice.updateAndGet(
                  v ->
                      v
                          + ((Integer.parseInt(itemEntity.getPrice())
                                  * orderItemCreateDto.getQuantity())
                              * discountPercent));

              return OrderItemEntity.builder()
                  .orderEntity(newOrderEntity)
                  .itemEntity(itemEntity)
                  .totalPrice(String.valueOf(totalPrice))
                  .quantity(orderItemCreateDto.getQuantity())
                  .createdAt(timestamp)
                  .build();
            }))
        .collect(Collectors.toList());
  }

  private OrderDto buildOrderDto(
      @NotNull OrderEntity savedOrderEntity, @NotNull AtomicReference<Double> discountPrice) {
    List<ItemDto> itemDtoList =
        savedOrderEntity.getOrderItemEntityList().stream()
            .map(
                orderItemEntity ->
                    ItemDto.builder()
                        .name(orderItemEntity.getItemEntity().getProductEntity().getName())
                        .sku(orderItemEntity.getItemEntity().getSku())
                        .quantity(String.valueOf(orderItemEntity.getQuantity()))
                        .unitAmount(
                            new AmountDto(
                                "USD",
                                String.valueOf(
                                    Double.parseDouble(orderItemEntity.getItemEntity().getPrice())),
                                null))
                        .build())
            .toList();

    return OrderDto.builder()
        .intent(PaypalOrderIntent.CAPTURE)
        .purchaseUnits(
            List.of(
                PurchaseUnitDto.builder()
                    .amount(
                        AmountDto.builder()
                            .value(savedOrderEntity.getGrandTotal())
                            .currencyCode("USD")
                            .breakdown(
                                BreakdownDto.builder()
                                    .discount(
                                        new DiscountDto("USD", String.valueOf(discountPrice.get())))
                                    .itemTotal(
                                        new ItemTotalDto(
                                            "USD",
                                            String.valueOf(
                                                discountPrice.get()
                                                    + Double.parseDouble(
                                                        savedOrderEntity.getGrandTotal()))))
                                    .build())
                            .build())
                    .items(itemDtoList)
                    .build()))
        .build();
  }

  @NotNull
  private OrderResponseDto sendCreateOrderRequestToPaypal(OrderDto orderRequest) {
    AccessTokenResponseDto accessTokenResponseDto =
        paypalClientInterface.sendGetAccessToken().bodyToMono(AccessTokenResponseDto.class).block();
    OrderResponseDto orderResponseDto =
        paypalClientInterface
            .sendCreateOrder(accessTokenResponseDto, orderRequest)
            .bodyToMono(OrderResponseDto.class)
            .block();

    if (orderResponseDto == null
        || orderResponseDto.getId() == null
        || orderResponseDto.getStatus() == null) {
      throw new InvalidOrderException("Order create response from paypal API is invalid");
    }

    return orderResponseDto;
  }

  @NotNull
  private OrderResponseDto sendCaptureOrderRequestToPaypal(UserPayOrderDto userPayOrderDto) {
    AccessTokenResponseDto accessTokenResponseDto =
        paypalClientInterface.sendGetAccessToken().bodyToMono(AccessTokenResponseDto.class).block();
    OrderResponseDto orderResponseDto =
        paypalClientInterface
            .sendCaptureOrder(accessTokenResponseDto, userPayOrderDto)
            .bodyToMono(OrderResponseDto.class)
            .block();

    if (orderResponseDto == null
        || orderResponseDto.getId() == null
        || orderResponseDto.getStatus() == null) {
      throw new InvalidOrderException("Order capture response from paypal API is invalid");
    }

    return orderResponseDto;
  }
}
