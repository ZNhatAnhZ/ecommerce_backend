package com.example.ecommerce_backend.model.dto.productentity;

import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityCreateDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductEntityCreateDto {

  private String name;

  private String description;

  private int categoryId;

  private int supplierId;

  private int discountId;

  private List<VariationEntityCreateDto> variationEntityCreateDtoList;
}
