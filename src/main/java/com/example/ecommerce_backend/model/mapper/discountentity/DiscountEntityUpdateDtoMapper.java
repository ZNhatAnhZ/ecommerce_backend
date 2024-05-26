package com.example.ecommerce_backend.model.mapper.discountentity;

import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityUpdateDtoMapper {

  DiscountEntity toEntity(DiscountEntityUpdateDto discountUpdateDto);

  DiscountEntityUpdateDto toDto(DiscountEntity discountEntity);
}
