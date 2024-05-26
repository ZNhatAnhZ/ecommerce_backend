package com.example.ecommerce_backend.model.mapper.variationentity;

import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityIndexDto;
import com.example.ecommerce_backend.model.entity.VariationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {VariationEntityIndexDtoMapper.class})
public interface VariationEntityIndexDtoMapper {

  VariationEntity toEntity(VariationEntityIndexDto variationEntityIndexDto);

  @Mapping(source = "childVariationEntityList", target = "childVariationEntityIndexDtoList")
  VariationEntityIndexDto toDto(VariationEntity variationEntity);
}
