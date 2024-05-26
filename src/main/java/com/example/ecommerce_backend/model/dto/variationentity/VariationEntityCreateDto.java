package com.example.ecommerce_backend.model.dto.variationentity;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariationEntityCreateDto {

  private String name;

  private String value;

  private List<VariationEntityCreateDto> variationEntityCreateDtoList;
}
