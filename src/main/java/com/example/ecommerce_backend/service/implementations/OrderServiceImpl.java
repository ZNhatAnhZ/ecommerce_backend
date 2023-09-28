package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.component.interfaces.PaypalClientInterface;
import com.example.ecommerce_backend.constant.PaypalOrderIntent;
import com.example.ecommerce_backend.dto.OrderEntity.OrderItemCreateDto;
import com.example.ecommerce_backend.dto.OrderEntity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.PaypalDTO.*;
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
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl {
    private final OrderEntityRepository orderEntityRepository;
    private final PaypalClientInterface paypalClientInterface;
    private final ItemServiceInterface itemServiceInterface;
    private final CartServiceInterface cartServiceInterface;
    private final UserServiceInterface userServiceInterface;

    public OrderResponseDto createOrder(List<OrderItemCreateDto> orderItemCreateDtoList) {
        if (orderItemCreateDtoList.isEmpty()) {
            throw new InvalidOrderException("Order item list is empty");
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        UserEntity userEntity = userServiceInterface.findUserById(orderItemCreateDtoList.get(0).getUserId());
        OrderEntity newOrderEntity = OrderEntity.builder()
                .userEntity(userEntity)
                .status("CREATED")
                .createdAt(timestamp)
                .build();
        AtomicReference<Double> grandTotal = new AtomicReference<>((double) 0);
        AtomicReference<Double> discountPrice = new AtomicReference<>((double) 0);

        List<OrderItemEntity> orderItemEntityList = orderItemCreateDtoList.stream().map((orderItemCreateDto -> {
            ItemEntity itemEntity = itemServiceInterface.findItemEntityById(orderItemCreateDto.getItemEntityId());

            if (orderItemCreateDto.getQuantity() > Integer.parseInt(itemEntity.getStock())) {
                throw new InvalidQuantityException("The requested item stock is smaller than the requested quantity");
            }

            if (orderItemCreateDto.getCartItemEntityId() != null) {
                cartServiceInterface.delete(orderItemCreateDto.getCartItemEntityId());
            }

            double discountPercent = Double.parseDouble(itemEntity.getProductEntity().getDiscountEntity().getDiscountPercent()) / 100;
            double totalPrice = (Integer.parseInt(itemEntity.getPrice()) * orderItemCreateDto.getQuantity()) * (1 - discountPercent);
            grandTotal.updateAndGet(v -> v + totalPrice);
            discountPrice.updateAndGet(v -> v + ((Integer.parseInt(itemEntity.getPrice()) * orderItemCreateDto.getQuantity()) * discountPercent));

            return OrderItemEntity.builder()
                    .orderEntity(newOrderEntity)
                    .itemEntity(itemEntity)
                    .totalPrice(String.valueOf(totalPrice))
                    .quantity(orderItemCreateDto.getQuantity())
                    .createdAt(timestamp)
                    .build();
        })).collect(Collectors.toList());

        newOrderEntity.setGrandTotal(String.valueOf(grandTotal.get()));
        newOrderEntity.setOrderItemEntityList(orderItemEntityList);
        OrderEntity savedOrderEntity = orderEntityRepository.save(newOrderEntity);

        List<ItemDto> itemDtoList = savedOrderEntity.getOrderItemEntityList().stream().map(orderItemEntity -> ItemDto.builder()
                .name(orderItemEntity.getItemEntity().getProductEntity().getName())
                .sku(orderItemEntity.getItemEntity().getSku())
                .quantity(String.valueOf(orderItemEntity.getQuantity()))
                .unitAmount(new AmountDto("USD", String.valueOf(Double.parseDouble(orderItemEntity.getItemEntity().getPrice())), null))
                .build()).toList();

        OrderDto orderRequest = OrderDto.builder()
                .intent(PaypalOrderIntent.CAPTURE)
                .purchaseUnits(List.of(PurchaseUnitDto.builder()
                        .amount(AmountDto.builder()
                                .value(savedOrderEntity.getGrandTotal())
                                .currencyCode("USD")
                                .breakdown(BreakdownDto.builder()
                                        .discount(new DiscountDto("USD", String.valueOf(discountPrice.get())))
                                        .itemTotal(new ItemTotalDto("USD", String.valueOf(discountPrice.get() + Double.parseDouble(savedOrderEntity.getGrandTotal()))))
                                        .build())
                                .build())
                        .items(itemDtoList)
                        .build()))
                .build();

        AccessTokenResponseDto accessTokenResponseDto = paypalClientInterface.sendGetAccessToken().bodyToMono(AccessTokenResponseDto.class).block();
        OrderResponseDto orderResponseDto = paypalClientInterface.sendCreateOrder(accessTokenResponseDto, orderRequest).bodyToMono(OrderResponseDto.class).block();

        if (orderResponseDto == null || orderResponseDto.getId() == null || orderResponseDto.getStatus() == null) {
            throw new InvalidOrderException("Order create response from paypal API is invalid");
        }

        savedOrderEntity.setStatus(orderResponseDto.getStatus());
        savedOrderEntity.setPaypalOrderId(orderResponseDto.getId());
        orderEntityRepository.save(savedOrderEntity);

        return orderResponseDto;
    }

    public OrderResponseDto payOrder(UserPayOrderDto userPayOrderDto) {
        AccessTokenResponseDto accessTokenResponseDto = paypalClientInterface.sendGetAccessToken().bodyToMono(AccessTokenResponseDto.class).block();
        OrderResponseDto orderResponseDto = paypalClientInterface.sendCaptureOrder(accessTokenResponseDto, userPayOrderDto).bodyToMono(OrderResponseDto.class).block();

        if (orderResponseDto == null || orderResponseDto.getId() == null || orderResponseDto.getStatus() == null) {
            throw new InvalidOrderException("Order capture response from paypal API is invalid");
        }

        Optional<OrderEntity> orderEntity = orderEntityRepository.findByPaypalOrderId(orderResponseDto.getId());

        if (orderEntity.isEmpty()) {
            throw new ResourceNotFoundException("Can not find order with paypal id: " + orderResponseDto.getId());
        }

        orderEntity.get().setStatus(orderResponseDto.getStatus());
        orderEntity.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        orderEntityRepository.save(orderEntity.get());

        return orderResponseDto;
    }
}
