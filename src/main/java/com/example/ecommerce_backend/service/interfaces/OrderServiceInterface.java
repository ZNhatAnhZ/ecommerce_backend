package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.OrderEntity.OrderItemCreateDto;
import com.example.ecommerce_backend.dto.OrderEntity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.PaypalDTO.OrderResponseDto;

import java.util.List;

public interface OrderServiceInterface {
    OrderResponseDto createOrder(List<OrderItemCreateDto> orderItemCreateDtoList);
    OrderResponseDto payOrder(UserPayOrderDto userPayOrderDto);
}
