package com.example.ecommerce_backend.model.mapper.discountentity;

import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.model.entity.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityCreateDtoMapper {

  DiscountEntity toEntity(DiscountEntityCreateDto discountEntityCreateDto);

  DiscountEntityCreateDto toDto(DiscountEntity discountEntity);
}
