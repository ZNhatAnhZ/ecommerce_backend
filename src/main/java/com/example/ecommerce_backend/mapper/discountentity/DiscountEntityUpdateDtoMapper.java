package com.example.ecommerce_backend.mapper.discountentity;

import com.example.ecommerce_backend.dto.discountentity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityUpdateDtoMapper {

  DiscountEntity toEntity(DiscountEntityUpdateDto discountUpdateDto);

  DiscountEntityUpdateDto toDto(DiscountEntity discountEntity);
}
