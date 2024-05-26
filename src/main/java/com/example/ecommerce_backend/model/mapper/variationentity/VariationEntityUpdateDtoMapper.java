package com.example.ecommerce_backend.model.mapper.variationentity;

import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.VariationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {VariationEntityCreateDtoMapper.class})
public interface VariationEntityUpdateDtoMapper {

  @Mapping(source = "variationEntityUpdateDtoList", target = "childVariationEntityList")
  VariationEntity toEntity(VariationEntityUpdateDto variationEntityUpdateDto);

  VariationEntityUpdateDto toDto(VariationEntity variationEntity);
}
