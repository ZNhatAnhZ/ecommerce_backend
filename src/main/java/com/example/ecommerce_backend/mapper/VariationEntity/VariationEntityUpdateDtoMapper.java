package com.example.ecommerce_backend.mapper.VariationEntity;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityUpdateDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VariationEntityCreateDtoMapper.class})
public interface VariationEntityUpdateDtoMapper {
    VariationEntityUpdateDto VariationEntityToVariationEntityUpdateDto(VariationEntity variationEntity);
    @Mapping(source = "variationEntityUpdateDto.variationEntityUpdateDtoList", target = "childVariationEntityList")
    VariationEntity VariationEntityUpdateDtoToVariationEntity(VariationEntityUpdateDto variationEntityUpdateDto);
    List<VariationEntity> VariationEntityUpdateDtoListToVariationEntityList(List<VariationEntityUpdateDto> variationEntityUpdateDtoList);
}
