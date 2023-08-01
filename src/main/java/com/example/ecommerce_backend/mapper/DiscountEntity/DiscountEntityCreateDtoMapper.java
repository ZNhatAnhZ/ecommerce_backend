package com.example.ecommerce_backend.mapper.DiscountEntity;

import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityCreateDtoMapper {
    DiscountEntity DiscountCreateDtoToDiscountEntity(DiscountEntityCreateDto discountEntityCreateDto);
    DiscountEntityCreateDto DiscountEntityToDiscountCreateDto(DiscountEntity discountEntity);
}
