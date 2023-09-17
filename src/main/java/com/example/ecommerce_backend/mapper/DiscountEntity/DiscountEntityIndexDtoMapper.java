package com.example.ecommerce_backend.mapper.DiscountEntity;

import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.mapper.ProductEntity.ProductEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.DiscountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductEntityIndexDtoMapper.class})
public interface DiscountEntityIndexDtoMapper {
    DiscountEntity discountEntityIndexDtoToDiscountEntity(DiscountEntityIndexDto DiscountEntityIndexDto);
    @Mapping(source = "discountEntity.productEntityList", target = "productEntityIndexDtoList")
    DiscountEntityIndexDto discountEntityToDiscountEntityIndexDto(DiscountEntity discountEntity);
}
