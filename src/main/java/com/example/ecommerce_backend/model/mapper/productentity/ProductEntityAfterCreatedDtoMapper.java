package com.example.ecommerce_backend.model.mapper.productentity;

import com.example.ecommerce_backend.model.dto.productentity.ProductEntityAfterCreatedDto;
import com.example.ecommerce_backend.model.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityAfterCreatedDtoMapper {

  ProductEntity toEntity(ProductEntityAfterCreatedDto productEntityAfterCreatedDto);

  ProductEntityAfterCreatedDto toDto(ProductEntity productEntity);
}
