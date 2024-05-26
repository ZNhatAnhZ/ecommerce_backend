package com.example.ecommerce_backend.model.mapper.variationentity;

import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityUpdateInfoDto;
import com.example.ecommerce_backend.model.entity.VariationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariationEntityUpdateInfoDtoMapper {

  VariationEntity toEntity(VariationEntityUpdateInfoDto variationEntityUpdateInfoDto);

  VariationEntityUpdateInfoDto toDto(VariationEntity variationEntity);
}
