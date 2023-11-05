package com.example.ecommerce_backend.mapper.variationentity;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityUpdateDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { VariationEntityCreateDtoMapper.class })
public interface VariationEntityUpdateDtoMapper {

	@Mapping(source = "variationEntityUpdateDto.variationEntityUpdateDtoList", target = "childVariationEntityList")
	VariationEntity toEntity(VariationEntityUpdateDto variationEntityUpdateDto);

	VariationEntityUpdateDto toDto(VariationEntity variationEntity);

}
