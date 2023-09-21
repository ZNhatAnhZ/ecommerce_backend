package com.example.ecommerce_backend.mapper.ProductEntity;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityAfterCreatedDto;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityAfterCreatedDtoMapper {
    ProductEntity toProductEntity(ProductEntityAfterCreatedDto productEntityAfterCreatedDto);
    ProductEntityAfterCreatedDto toProductEntityAfterCreatedDto(ProductEntity productEntity);
}
