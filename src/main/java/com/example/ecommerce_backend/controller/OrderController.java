package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.orderentity.OrderEntityCreateDto;
import com.example.ecommerce_backend.dto.orderentity.OrderEntityIndexDto;
import com.example.ecommerce_backend.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.mapper.orderentity.OrderEntityIndexMapper;
import com.example.ecommerce_backend.service.implementations.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/orders")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

  private final OrderEntityIndexMapper orderEntityIndexMapper;

  private final OrderServiceImpl orderService;

  @PostMapping
  public ResponseEntity<OrderEntityIndexDto> createOrder(
      @RequestBody OrderEntityCreateDto orderEntityCreateDto) {
    return ResponseEntity.ok(
        orderEntityIndexMapper.toDto(orderService.createOrder(orderEntityCreateDto)));
  }

  @PostMapping("/{id}/confirm")
  public ResponseEntity<OrderEntityIndexDto> payOrder(
      @PathVariable String id, @RequestBody UserPayOrderDto userPayOrderDto) {
    userPayOrderDto.setOrderId(id);
    return ResponseEntity.ok(orderEntityIndexMapper.toDto(orderService.payOrder(userPayOrderDto)));
  }
}
