package com.example.ecommerce_backend.mapper.ProductEntity;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityIndexDto;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityIndexDtoMapper {
    ProductEntity ProductEntityIndexDtoToProductEntity(ProductEntityIndexDto productEntityIndexDto);
    ProductEntityIndexDto ProductEntityToProductEntityIndexDto(ProductEntity productEntity);
}
