package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.CartItemEntity.CartItemEntityCreateRequestDto;
import com.example.ecommerce_backend.dto.CartItemEntity.CartItemEntityIndexDto;
import com.example.ecommerce_backend.dto.OrderEntity.OrderItemCreateDto;
import com.example.ecommerce_backend.dto.OrderEntity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.PaypalDTO.OrderResponseDto;
import com.example.ecommerce_backend.service.implementations.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/orders")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody List<OrderItemCreateDto> orderItemCreateDtoList) {
        return ResponseEntity.ok(orderService.createOrder(orderItemCreateDtoList));
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<OrderResponseDto> payOrder(@PathVariable String id, @RequestBody UserPayOrderDto userPayOrderDto) {
        return ResponseEntity.ok(orderService.payOrder(userPayOrderDto));
    }
}
