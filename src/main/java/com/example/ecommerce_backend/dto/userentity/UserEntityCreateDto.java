package com.example.ecommerce_backend.dto.userentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntityCreateDto {

  private String username;

  private String password;

  private String firstName;

  private String lastName;

  private String telephone;

  private String email;
}
