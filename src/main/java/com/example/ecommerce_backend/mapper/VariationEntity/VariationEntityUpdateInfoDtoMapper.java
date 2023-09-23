package com.example.ecommerce_backend.mapper.VariationEntity;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityUpdateInfoDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariationEntityUpdateInfoDtoMapper {
    VariationEntity toVariationEntity(VariationEntityUpdateInfoDto variationEntityUpdateInfoDto);
    VariationEntityUpdateInfoDto toVariationEntityUpdateInfoDto(VariationEntity variationEntity);
}
