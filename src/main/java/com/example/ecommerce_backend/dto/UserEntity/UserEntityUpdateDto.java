package com.example.ecommerce_backend.dto.UserEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntityUpdateDto {
    private int id;
    private String firstName;
    private String lastName;
    private String telephone;
}
