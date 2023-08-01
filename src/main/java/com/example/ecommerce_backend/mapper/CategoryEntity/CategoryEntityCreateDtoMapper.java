package com.example.ecommerce_backend.mapper.CategoryEntity;

import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityCreateDtoMapper {
    ProductCategoryEntity CategoryEntityCreateDtoToProductCategoryEntity(CategoryEntityCreateDto categoryEntityCreateDto);
    CategoryEntityCreateDto ProductCategoryEntityToCategoryEntityCreateDto(ProductCategoryEntity productCategoryEntity);
}
