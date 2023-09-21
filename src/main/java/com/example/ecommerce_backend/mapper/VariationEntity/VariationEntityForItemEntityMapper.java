package com.example.ecommerce_backend.mapper.VariationEntity;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityForItemEntityDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariationEntityForItemEntityMapper {
    VariationEntity toVariationEntity(VariationEntityForItemEntityDto variationEntityForItemEntityDto);
    VariationEntityForItemEntityDto toVariationEntityForItemEntityDto(VariationEntity variationEntity);
}
