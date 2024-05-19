package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.orderentity.OrderEntityCreateDto;
import com.example.ecommerce_backend.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.model.OrderEntity;

public interface OrderServiceInterface {

  OrderEntity createOrder(OrderEntityCreateDto orderEntityCreateDto);

  OrderEntity payOrder(UserPayOrderDto userPayOrderDto);
}
