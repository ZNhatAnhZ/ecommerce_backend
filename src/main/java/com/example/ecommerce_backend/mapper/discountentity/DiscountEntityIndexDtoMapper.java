package com.example.ecommerce_backend.mapper.discountentity;

import com.example.ecommerce_backend.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityIndexDtoMapper {

	DiscountEntity toEntity(DiscountEntityIndexDto discountEntityIndexDto);

	DiscountEntityIndexDto toDto(DiscountEntity discountEntity);

}
