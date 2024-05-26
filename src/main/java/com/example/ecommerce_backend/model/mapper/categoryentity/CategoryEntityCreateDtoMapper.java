package com.example.ecommerce_backend.model.mapper.categoryentity;

import com.example.ecommerce_backend.model.dto.productcategoryentity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.model.entity.ProductCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityCreateDtoMapper {

  ProductCategoryEntity toEntity(CategoryEntityCreateDto categoryEntityCreateDto);

  CategoryEntityCreateDto toDto(ProductCategoryEntity productCategoryEntity);
}
