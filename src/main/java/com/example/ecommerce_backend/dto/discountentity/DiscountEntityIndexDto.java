package com.example.ecommerce_backend.dto.discountentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiscountEntityIndexDto {

  private int id;

  private String name;

  private String description;

  private String discountPercent;

  private String active;
}
