package com.example.ecommerce_backend.dto.itementity;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityForItemEntityDto;
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
