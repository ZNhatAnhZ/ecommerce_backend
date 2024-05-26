package com.example.ecommerce_backend.util.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaypalEndpoints {
  ACCESS_TOKEN_URL("/v1/oauth2/token"),
  CREATE_ORDER_URL("/v2/checkout/orders"),
  CAPTURE_ORDER_URL("/v2/checkout/orders/{orderID}/capture");

  private final String path;
}
