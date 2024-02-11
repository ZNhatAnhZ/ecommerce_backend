package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.paypaldto.OrderResponseDto;

public interface OrderServiceInterface {

  // OrderResponseDto createOrder(List<OrderItemCreateDto> orderItemCreateDtoList);
  OrderResponseDto payOrder(UserPayOrderDto userPayOrderDto);
}
