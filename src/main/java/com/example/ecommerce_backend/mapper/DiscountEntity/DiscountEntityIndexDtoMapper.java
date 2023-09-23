package com.example.ecommerce_backend.mapper.DiscountEntity;

import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityIndexDtoMapper {
    DiscountEntity discountEntityIndexDtoToDiscountEntity(DiscountEntityIndexDto DiscountEntityIndexDto);
    DiscountEntityIndexDto discountEntityToDiscountEntityIndexDto(DiscountEntity discountEntity);
}
