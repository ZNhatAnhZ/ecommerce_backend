package com.example.ecommerce_backend.mapper.variationentity;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityIndexDto;
import com.example.ecommerce_backend.model.VariationEntity;
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
