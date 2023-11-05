package com.example.ecommerce_backend.mapper.productentity;

import com.example.ecommerce_backend.dto.productentity.ProductEntityUpdateDto;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityUpdateDtoMapper {

	ProductEntity toEntity(ProductEntityUpdateDto productEntityUpdateDto);

}
