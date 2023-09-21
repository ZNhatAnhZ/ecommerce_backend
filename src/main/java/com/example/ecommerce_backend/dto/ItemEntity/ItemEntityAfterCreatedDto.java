package com.example.ecommerce_backend.dto.ItemEntity;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityForItemEntityDto;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ItemEntityAfterCreatedDto {
    private int id;
    private String sku;
    private Set<VariationEntityForItemEntityDto> variationEntityList;
}
