package com.example.ecommerce_backend.mapper.ProductVariationEntity;

import com.example.ecommerce_backend.dto.ProductVariationEntity.ProductVariationDetailDto;
import com.example.ecommerce_backend.model.ProductVariationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductVariationEntityDetailDtoMapper {
    @Mapping(target = "attribute", source = "productVariationEntity.variationEntity.attribute")
    @Mapping(target = "value", source = "productVariationEntity.variationEntity.value")
    ProductVariationDetailDto ProductVariationEntityToProductVariationDetailDto(ProductVariationEntity productVariationEntity);
}
