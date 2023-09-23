package com.example.ecommerce_backend.mapper.VariationEntity;

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
    VariationEntityIndexDto ToVariationEntityIndexDto(VariationEntity variationEntity);
    VariationEntity ToVariationEntity(VariationEntityIndexDto variationEntityIndexDto);
    List<VariationEntityIndexDto> ToVariationEntityIndexDtoList(List<VariationEntity> variationEntityList);
    Set<VariationEntityIndexDto> ToVariationEntityIndexDtoSet(Set<VariationEntity> variationEntitySet);
}
