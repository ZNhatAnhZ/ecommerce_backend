package com.example.ecommerce_backend.model.mapper.variationentity;

import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityForItemEntityDto;
import com.example.ecommerce_backend.model.entity.VariationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariationEntityForItemEntityMapper {

  VariationEntity toEntity(VariationEntityForItemEntityDto variationEntityForItemEntityDto);

  VariationEntityForItemEntityDto toDto(VariationEntity variationEntity);
}
