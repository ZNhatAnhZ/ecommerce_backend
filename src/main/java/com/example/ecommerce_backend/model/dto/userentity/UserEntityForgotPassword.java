package com.example.ecommerce_backend.model.dto.userentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntityForgotPassword {

  private String email;
}
