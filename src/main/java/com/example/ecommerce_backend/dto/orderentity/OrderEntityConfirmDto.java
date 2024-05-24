package com.example.ecommerce_backend.dto.orderentity;

import com.example.ecommerce_backend.constant.OrderStatus;
import lombok.Data;

@Data
public class OrderEntityConfirmDto {
  Integer orderId;
  OrderStatus status;
}
