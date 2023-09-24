package com.example.ecommerce_backend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaypalEndpoints {
    ACCESS_TOKEN_URL("/v1/oauth2/token"),
    CREATE_ORDER_URL("/v2/checkout/orders");

    private final String path;
}
