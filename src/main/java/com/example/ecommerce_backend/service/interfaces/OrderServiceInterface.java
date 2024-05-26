package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.dto.orderentity.OrderEntityConfirmDto;
import com.example.ecommerce_backend.model.dto.orderentity.OrderEntityCreateDto;
import com.example.ecommerce_backend.model.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.model.entity.OrderEntity;
import com.example.ecommerce_backend.util.constant.OrderStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderServiceInterface {

  List<OrderEntity> confirmOrders(List<OrderEntityConfirmDto> orderEntityConfirmDtoList);

  Page<OrderEntity> findAllByStatus(OrderStatus orderStatus, Pageable pageable);

  OrderEntity createOrder(OrderEntityCreateDto orderEntityCreateDto);

  OrderEntity payOrder(UserPayOrderDto userPayOrderDto);
}
