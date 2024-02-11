package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.orderentity.OrderEntityCreateWithEmailDto;
import com.example.ecommerce_backend.dto.orderentity.OrderEntityCreateWithUserIdDto;
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

  @PostMapping("/with_user")
  public ResponseEntity<OrderResponseDto> createOrder(
      @RequestBody OrderEntityCreateWithUserIdDto orderEntityCreateWithUserIdDto) {
    return ResponseEntity.ok(orderService.createOrderForUser(orderEntityCreateWithUserIdDto));
  }

  @PostMapping("/with_email")
  public ResponseEntity<OrderResponseDto> createOrder(
      @RequestBody OrderEntityCreateWithEmailDto orderEntityCreateWithEmailDto) {
    return ResponseEntity.ok(orderService.createOrderForEmail(orderEntityCreateWithEmailDto));
  }

  @PostMapping("/{id}/confirm")
  public ResponseEntity<OrderResponseDto> payOrder(
      @PathVariable String id, @RequestBody UserPayOrderDto userPayOrderDto) {
    return ResponseEntity.ok(orderService.payOrder(userPayOrderDto));
  }
}
