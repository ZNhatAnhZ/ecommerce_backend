package com.example.ecommerce_backend.constant;

import lombok.Getter;

@Getter
public enum OrderStatus {
  CREATED,
  PAID,
  TRACKING,
  CANCELLED,
  COMPLETED,
  ERRORED,
  REFUNDED;
}
