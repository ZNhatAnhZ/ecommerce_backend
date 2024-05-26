package com.example.ecommerce_backend.model.mapper.productentity;

import com.example.ecommerce_backend.model.dto.productentity.ProductEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityUpdateDtoMapper {

  ProductEntity toEntity(ProductEntityUpdateDto productEntityUpdateDto);
}
