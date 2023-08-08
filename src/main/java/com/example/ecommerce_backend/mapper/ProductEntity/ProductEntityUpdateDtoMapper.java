package com.example.ecommerce_backend.mapper.ProductEntity;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityUpdateDto;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityUpdateDtoMapper {
    ProductEntity ProductEntityUpdateDtoToProductEntity(ProductEntityUpdateDto productEntityUpdateDto);
}
