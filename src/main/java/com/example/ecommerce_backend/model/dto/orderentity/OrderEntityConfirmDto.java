package com.example.ecommerce_backend.model.dto.orderentity;

import com.example.ecommerce_backend.util.constant.OrderStatus;
import lombok.Data;

@Data
public class OrderEntityConfirmDto {
  Integer orderId;
  OrderStatus status;
}
