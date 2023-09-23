package com.example.ecommerce_backend.mapper.ProductEntity;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityIndexDto;
import com.example.ecommerce_backend.mapper.CategoryEntity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.DiscountEntity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.SupplierEntity.SupplierEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryEntityCreateDtoMapper.class, SupplierEntityCreateDtoMapper.class, DiscountEntityIndexDtoMapper.class})
public interface ProductEntityIndexDtoMapper {
    ProductEntity toProductEntity(ProductEntityIndexDto productEntityIndexDto);
    ProductEntityIndexDto toProductEntityIndexDto(ProductEntity productEntity);
}
