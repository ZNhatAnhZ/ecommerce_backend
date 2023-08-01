package com.example.ecommerce_backend.mapper.CategoryEntity;

import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityUpdateDto;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityUpdateDtoMapper {
    ProductCategoryEntity CategoryEntityUpdateDtoToProductCategoryEntity(CategoryEntityUpdateDto categoryEntityUpdateDto);
    CategoryEntityUpdateDto ProductCategoryEntityToCategoryEntityUpdateDto(ProductCategoryEntity productCategoryEntity);
}
