package com.example.ecommerce_backend.model.mapper.categoryentity;

import com.example.ecommerce_backend.model.dto.productcategoryentity.CategoryEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.ProductCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityUpdateDtoMapper {

  ProductCategoryEntity toEntity(CategoryEntityUpdateDto categoryEntityUpdateDto);

  CategoryEntityUpdateDto toDto(ProductCategoryEntity productCategoryEntity);
}
