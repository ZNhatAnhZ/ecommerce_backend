package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.orderentity.OrderEntityCreateDto;
import com.example.ecommerce_backend.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.paypaldto.OrderResponseDto;
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

  private final OrderServiceImpl orderService;

  @PostMapping
  public ResponseEntity<OrderResponseDto> createOrder(
      @RequestBody OrderEntityCreateDto orderEntityCreateDto) {
    return ResponseEntity.ok(orderService.createOrder(orderEntityCreateDto));
  }

  @PostMapping("/{id}/confirm")
  public ResponseEntity<OrderResponseDto> payOrder(
      @PathVariable String id, @RequestBody UserPayOrderDto userPayOrderDto) {
    userPayOrderDto.setOrderId(id);
    return ResponseEntity.ok(orderService.payOrder(userPayOrderDto));
  }
}
