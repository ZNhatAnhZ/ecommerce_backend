package com.example.ecommerce_backend.mapper.variationentity;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityUpdateInfoDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariationEntityUpdateInfoDtoMapper {

	VariationEntity toEntity(VariationEntityUpdateInfoDto variationEntityUpdateInfoDto);

	VariationEntityUpdateInfoDto toDto(VariationEntity variationEntity);

}
