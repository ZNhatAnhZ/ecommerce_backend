package com.example.ecommerce_backend.model.mapper.variationentity;

import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityCreateDto;
import com.example.ecommerce_backend.model.entity.VariationEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {VariationEntityCreateDtoMapper.class})
public interface VariationEntityCreateDtoMapper {

  @Mapping(source = "variationEntityCreateDtoList", target = "childVariationEntityList")
  VariationEntity toEntity(VariationEntityCreateDto variationEntityCreateDto);

  VariationEntityCreateDto toDto(VariationEntity variationEntity);

  List<VariationEntity> toEntityList(List<VariationEntityCreateDto> variationEntityCreateDtoList);
}
