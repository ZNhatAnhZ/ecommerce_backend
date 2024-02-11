package com.example.ecommerce_backend.mapper.discountentity;

import com.example.ecommerce_backend.dto.discountentity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityCreateDtoMapper {

  DiscountEntity toEntity(DiscountEntityCreateDto discountEntityCreateDto);

  DiscountEntityCreateDto toDto(DiscountEntity discountEntity);
}
