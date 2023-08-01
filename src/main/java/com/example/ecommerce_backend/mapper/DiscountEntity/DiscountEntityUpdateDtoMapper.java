package com.example.ecommerce_backend.mapper.DiscountEntity;

import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityUpdateDtoMapper {
    DiscountEntity DiscountUpdateDtoToDiscountEntity(DiscountEntityUpdateDto discountUpdateDto);
    DiscountEntityUpdateDto DiscountEntityToDiscountUpdateDto(DiscountEntity discountEntity);
}
