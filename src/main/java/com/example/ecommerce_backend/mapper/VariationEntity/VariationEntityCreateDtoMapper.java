package com.example.ecommerce_backend.mapper.VariationEntity;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityCreateDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VariationEntityCreateDtoMapper.class})
public interface VariationEntityCreateDtoMapper {
    VariationEntityCreateDto VariationEntityToVariationEntityCreateDto(VariationEntity variationEntity);
    @Mapping(source = "variationEntityCreateDto.variationEntityCreateDtoList", target = "childVariationEntityList")
    VariationEntity VariationEntityCreateDtoToVariationEntity(VariationEntityCreateDto variationEntityCreateDto);
    List<VariationEntity> VariationEntityCreateDtoListToVariationEntityList(List<VariationEntityCreateDto> variationEntityCreateDtoList);
}
