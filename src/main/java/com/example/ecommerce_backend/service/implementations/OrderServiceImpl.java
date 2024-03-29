package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.component.interfaces.PaypalClientInterface;
import com.example.ecommerce_backend.constant.PaypalOrderIntent;
import com.example.ecommerce_backend.dto.orderentity.OrderEntityCreateWithEmailDto;
import com.example.ecommerce_backend.dto.orderentity.OrderEntityCreateWithUserIdDto;
import com.example.ecommerce_backend.dto.orderentity.OrderItemEntityCreateDto;
import com.example.ecommerce_backend.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.paypaldto.*;
import com.example.ecommerce_backend.exception.InvalidOrderException;
import com.example.ecommerce_backend.exception.InvalidQuantityException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.model.ItemEntity;
import com.example.ecommerce_backend.model.OrderEntity;
import com.example.ecommerce_backend.model.OrderItemEntity;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.repository.OrderEntityRepository;
import com.example.ecommerce_backend.service.interfaces.CartServiceInterface;
import com.example.ecommerce_backend.service.interfaces.ItemServiceInterface;
import com.example.ecommerce_backend.service.interfaces.OrderServiceInterface;
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderServiceInterface {

  private final OrderEntityRepository orderEntityRepository;

  private final PaypalClientInterface paypalClientInterface;

  private final ItemServiceInterface itemServiceInterface;

  private final CartServiceInterface cartServiceInterface;

  private final UserServiceInterface userServiceInterface;

  // @Override
  public OrderResponseDto createOrderForUser(
      OrderEntityCreateWithUserIdDto orderEntityCreateWithUserIdDto) {
    AtomicReference<Double> grandTotal = new AtomicReference<>((double) 0);
    AtomicReference<Double> discountPrice = new AtomicReference<>((double) 0);

    OrderEntity savedOrderEntity =
        buildOrderWithUserIdAndSaveToLocalDB(
            orderEntityCreateWithUserIdDto, grandTotal, discountPrice);
    OrderDto orderRequest = buildOrderDto(savedOrderEntity, discountPrice);
    OrderResponseDto orderResponseDto = sendCreateOrderRequestToPaypal(orderRequest);

    savedOrderEntity.setStatus(orderResponseDto.getStatus());
    savedOrderEntity.setPaypalOrderId(orderResponseDto.getId());
    orderEntityRepository.save(savedOrderEntity);

    return orderResponseDto;
  }

  // @Override
  public OrderResponseDto createOrderForEmail(
      OrderEntityCreateWithEmailDto orderEntityCreateWithEmailDto) {
    AtomicReference<Double> grandTotal = new AtomicReference<>((double) 0);
    AtomicReference<Double> discountPrice = new AtomicReference<>((double) 0);

    OrderEntity savedOrderEntity =
        buildOrderWithEmailAndSaveToLocalDB(
            orderEntityCreateWithEmailDto, grandTotal, discountPrice);
    OrderDto orderRequest = buildOrderDto(savedOrderEntity, discountPrice);
    OrderResponseDto orderResponseDto = sendCreateOrderRequestToPaypal(orderRequest);

    savedOrderEntity.setStatus(orderResponseDto.getStatus());
    savedOrderEntity.setPaypalOrderId(orderResponseDto.getId());
    orderEntityRepository.save(savedOrderEntity);

    return orderResponseDto;
  }

  @Override
  public OrderResponseDto payOrder(UserPayOrderDto userPayOrderDto) {
    OrderResponseDto orderResponseDto = sendCaptureOrderRequestToPaypal(userPayOrderDto);

    Optional<OrderEntity> orderEntity =
        orderEntityRepository.findByPaypalOrderId(orderResponseDto.getId());
    if (orderEntity.isEmpty()) {
      throw new ResourceNotFoundException(
          "Can not find order with paypal id: " + orderResponseDto.getId());
    }

    orderEntity.get().setStatus(orderResponseDto.getStatus());
    orderEntity.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    orderEntityRepository.save(orderEntity.get());

    return orderResponseDto;
  }

  private OrderEntity buildOrderWithEmailAndSaveToLocalDB(
      OrderEntityCreateWithEmailDto orderEntityCreateWithEmailDto,
      AtomicReference<Double> grandTotal,
      AtomicReference<Double> discountPrice) {
    OrderEntity newOrderEntity =
        OrderEntity.builder()
            .email(orderEntityCreateWithEmailDto.getEmail())
            .status("CREATED")
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .build();

    List<OrderItemEntity> orderItemEntityList =
        buildListOfOrderItemEntity(
            orderEntityCreateWithEmailDto.getItemList(), grandTotal, discountPrice, newOrderEntity);

    newOrderEntity.setGrandTotal(String.valueOf(grandTotal.get()));
    newOrderEntity.setOrderItemEntityList(orderItemEntityList);
    return orderEntityRepository.save(newOrderEntity);
  }

  private OrderEntity buildOrderWithUserIdAndSaveToLocalDB(
      OrderEntityCreateWithUserIdDto orderEntityCreateWithUserIdDto,
      AtomicReference<Double> grandTotal,
      AtomicReference<Double> discountPrice) {
    UserEntity userEntity =
        userServiceInterface.findUserById(orderEntityCreateWithUserIdDto.getUserId());
    OrderEntity newOrderEntity =
        OrderEntity.builder()
            .userEntity(userEntity)
            .email(userEntity.getEmail())
            .status("CREATED")
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .build();

    List<OrderItemEntity> orderItemEntityList =
        buildListOfOrderItemEntity(
            orderEntityCreateWithUserIdDto.getItemList(),
            grandTotal,
            discountPrice,
            newOrderEntity);

    newOrderEntity.setGrandTotal(String.valueOf(grandTotal.get()));
    newOrderEntity.setOrderItemEntityList(orderItemEntityList);
    return orderEntityRepository.save(newOrderEntity);
  }

  private List<OrderItemEntity> buildListOfOrderItemEntity(
      List<OrderItemEntityCreateDto> orderItemEntityCreateDtoList,
      AtomicReference<Double> grandTotal,
      AtomicReference<Double> discountPrice,
      OrderEntity newOrderEntity) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    return orderItemEntityCreateDtoList.stream()
        .map(
            (orderItemCreateDto -> {
              ItemEntity itemEntity =
                  itemServiceInterface.findItemEntityById(orderItemCreateDto.getItemEntityId());

              if (orderItemCreateDto.getQuantity() > Integer.parseInt(itemEntity.getStock())) {
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
      OrderEntity savedOrderEntity, AtomicReference<Double> discountPrice) {
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
