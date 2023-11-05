package com.example.ecommerce_backend.mapper.variationentity;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityForItemEntityDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariationEntityForItemEntityMapper {

	VariationEntity toEntity(VariationEntityForItemEntityDto variationEntityForItemEntityDto);

	VariationEntityForItemEntityDto toDto(VariationEntity variationEntity);

}
