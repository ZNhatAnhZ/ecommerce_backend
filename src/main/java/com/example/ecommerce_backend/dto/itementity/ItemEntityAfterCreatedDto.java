package com.example.ecommerce_backend.dto.itementity;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityForItemEntityDto;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemEntityAfterCreatedDto {

  private int id;

  private String sku;

  private Set<VariationEntityForItemEntityDto> variationEntityList;
}
