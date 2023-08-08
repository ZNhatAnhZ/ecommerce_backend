package com.example.ecommerce_backend.mapper.VariationEntity;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityCreateDto;
import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityIndexDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {VariationEntityIndexDtoMapper.class})
public interface VariationEntityIndexDtoMapper {
    @Mapping(source = "variationEntity.childVariationEntityList", target = "childVariationEntityIndexDtoList")
//    @Mapping(source = "variationEntity.parentVariationEntity", target = "parentVariationEntityIndexDto")
    VariationEntityIndexDto VariationEntityToVariationEntityIndexDto(VariationEntity variationEntity);
    VariationEntity VariationEntityIndexDtoToVariationEntity(VariationEntityIndexDto variationEntityIndexDto);
    List<VariationEntityIndexDto> VariationEntityListToVariationEntityIndexDtoList(List<VariationEntity> variationEntityList);
    Set<VariationEntityIndexDto> VariationEntitySetToVariationEntityIndexDtoSet(Set<VariationEntity> variationEntitySet);
}
