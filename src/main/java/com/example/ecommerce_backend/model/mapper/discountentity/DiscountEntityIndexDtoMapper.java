package com.example.ecommerce_backend.model.mapper.discountentity;

import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.model.entity.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityIndexDtoMapper {

  DiscountEntity toEntity(DiscountEntityIndexDto discountEntityIndexDto);

  DiscountEntityIndexDto toDto(DiscountEntity discountEntity);
}
