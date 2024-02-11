package com.example.ecommerce_backend.mapper.categoryentity;

import com.example.ecommerce_backend.dto.productcategoryentity.CategoryEntityUpdateDto;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityUpdateDtoMapper {

  ProductCategoryEntity toEntity(CategoryEntityUpdateDto categoryEntityUpdateDto);

  CategoryEntityUpdateDto toDto(ProductCategoryEntity productCategoryEntity);
}
