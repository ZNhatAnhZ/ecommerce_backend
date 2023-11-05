package com.example.ecommerce_backend.mapper.variationentity;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityCreateDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { VariationEntityCreateDtoMapper.class })
public interface VariationEntityCreateDtoMapper {

	@Mapping(source = "variationEntityCreateDto.variationEntityCreateDtoList", target = "childVariationEntityList")
	VariationEntity toEntity(VariationEntityCreateDto variationEntityCreateDto);

	VariationEntityCreateDto toDto(VariationEntity variationEntity);

	List<VariationEntity> toEntityList(
			List<VariationEntityCreateDto> variationEntityCreateDtoList);

}
