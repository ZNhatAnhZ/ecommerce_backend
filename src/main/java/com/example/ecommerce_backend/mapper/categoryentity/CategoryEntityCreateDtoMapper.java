package com.example.ecommerce_backend.mapper.categoryentity;

import com.example.ecommerce_backend.dto.productcategoryentity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityCreateDtoMapper {

	ProductCategoryEntity toEntity(
			CategoryEntityCreateDto categoryEntityCreateDto);

	CategoryEntityCreateDto toDto(ProductCategoryEntity productCategoryEntity);

}
