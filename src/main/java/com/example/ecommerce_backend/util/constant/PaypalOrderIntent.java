package com.example.ecommerce_backend.util.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaypalOrderIntent {
  CAPTURE("CAPTURE"),
  AUTHORIZE("AUTHORIZE");

  private final String value;
}
