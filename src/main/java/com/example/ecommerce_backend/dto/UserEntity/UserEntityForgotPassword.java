package com.example.ecommerce_backend.dto.UserEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntityForgotPassword {
    private String email;
}
