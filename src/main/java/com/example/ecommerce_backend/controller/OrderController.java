package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.constant.OrderStatus;
import com.example.ecommerce_backend.dto.orderentity.*;
import com.example.ecommerce_backend.mapper.orderentity.OrderEntityIndexExtendedMapper;
import com.example.ecommerce_backend.mapper.orderentity.OrderEntityIndexMapper;
import com.example.ecommerce_backend.service.implementations.OrderServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/orders")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

  private final OrderEntityIndexExtendedMapper orderEntityIndexExtendedMapper;

  private final OrderEntityIndexMapper orderEntityIndexMapper;

  private final OrderServiceImpl orderService;

  @PostMapping
  public ResponseEntity<OrderEntityIndexDto> createOrder(
      @RequestBody OrderEntityCreateDto orderEntityCreateDto) {
    return ResponseEntity.ok(
        orderEntityIndexMapper.toDto(orderService.createOrder(orderEntityCreateDto)));
  }

  @PostMapping("/{id}/purchase")
  public ResponseEntity<OrderEntityIndexDto> payOrder(
      @PathVariable String id, @RequestBody UserPayOrderDto userPayOrderDto) {
    userPayOrderDto.setOrderId(id);
    return ResponseEntity.ok(orderEntityIndexMapper.toDto(orderService.payOrder(userPayOrderDto)));
  }

  @GetMapping
  public ResponseEntity<Page<OrderEntityIndexExtendedDto>> getAllOrdersByStatus(
      @RequestParam OrderStatus orderStatus,
      @PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(
        orderService
            .findAllByStatus(orderStatus, pageable)
            .map(orderEntityIndexExtendedMapper::toDto));
  }

  @PostMapping("/confirm")
  public ResponseEntity<List<OrderEntityIndexExtendedDto>> confirmOrder(
      @RequestBody List<OrderEntityConfirmDto> orderEntityConfirmDtoList) {
    return ResponseEntity.ok(
        orderService.confirmOrders(orderEntityConfirmDtoList).stream()
            .map(orderEntityIndexExtendedMapper::toDto)
            .toList());
  }
}
