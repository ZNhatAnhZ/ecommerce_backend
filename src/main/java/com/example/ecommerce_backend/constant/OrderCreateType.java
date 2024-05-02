package com.example.ecommerce_backend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderCreateType {
  WITH_USERID,
  WITH_EMAIL;
}
