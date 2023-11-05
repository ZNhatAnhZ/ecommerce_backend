package com.example.ecommerce_backend.dto.variationentity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VariationEntityCreateDto {

	private String name;

	private String value;

	private List<VariationEntityCreateDto> variationEntityCreateDtoList;

}
