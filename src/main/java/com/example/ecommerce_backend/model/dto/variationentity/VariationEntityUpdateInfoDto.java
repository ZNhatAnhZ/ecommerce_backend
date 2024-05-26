package com.example.ecommerce_backend.model.dto.variationentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariationEntityUpdateInfoDto {

  private int id;

  private String name;

  private String value;
}
