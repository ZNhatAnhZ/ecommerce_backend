package com.example.ecommerce_backend.mapper.ProductEntity;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityDetailDto;
import com.example.ecommerce_backend.mapper.ProductVariationEntity.ProductVariationEntityDetailDtoMapper;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductVariationEntityDetailDtoMapper.class})
public interface ProductEntityDetailDtoMapper {
    ProductEntity ProductEntityDetailDtoToProductEntity(ProductEntityDetailDto productEntityDetailDto);
    ProductEntityDetailDto ProductEntityToProductEntityDetailDto(ProductEntity productEntity);
}
