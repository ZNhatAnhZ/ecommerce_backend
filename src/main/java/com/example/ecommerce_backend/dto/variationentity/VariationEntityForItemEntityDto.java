package com.example.ecommerce_backend.dto.variationentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariationEntityForItemEntityDto {

  private int id;

  private String name;

  private String value;
}
