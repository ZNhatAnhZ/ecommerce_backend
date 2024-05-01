package com.example.ecommerce_backend.mapper.variationentity;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityFlatIndexDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface VariationEntityFlatIndexDtoMapper {
  @Mapping(source = "parentVariationEntityId", target = "parentVariationEntity.id")
  VariationEntity toEntity(VariationEntityFlatIndexDto variationEntityFlatIndexDto);

  @Mapping(source = "parentVariationEntity.id", target = "parentVariationEntityId")
  VariationEntityFlatIndexDto toDto(VariationEntity variationEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(source = "parentVariationEntityId", target = "parentVariationEntity.id")
  VariationEntity partialUpdate(
      VariationEntityFlatIndexDto variationEntityFlatIndexDto,
      @MappingTarget VariationEntity variationEntity);
}
