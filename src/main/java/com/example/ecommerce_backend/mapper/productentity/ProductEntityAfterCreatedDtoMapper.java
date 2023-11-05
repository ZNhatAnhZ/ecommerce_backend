package com.example.ecommerce_backend.mapper.productentity;

import com.example.ecommerce_backend.dto.productentity.ProductEntityAfterCreatedDto;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityAfterCreatedDtoMapper {

	ProductEntity toEntity(ProductEntityAfterCreatedDto productEntityAfterCreatedDto);

	ProductEntityAfterCreatedDto toDto(ProductEntity productEntity);

}
