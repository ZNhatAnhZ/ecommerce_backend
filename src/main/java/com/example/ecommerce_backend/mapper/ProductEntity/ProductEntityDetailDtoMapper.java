package com.example.ecommerce_backend.mapper.ProductEntity;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityDetailDto;
import com.example.ecommerce_backend.mapper.CategoryEntity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.DiscountEntity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.SupplierEntity.SupplierEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {VariationEntityIndexDtoMapper.class, CategoryEntityCreateDtoMapper.class,
                SupplierEntityCreateDtoMapper.class, DiscountEntityIndexDtoMapper.class})
public interface ProductEntityDetailDtoMapper {
    ProductEntity ProductEntityDetailDtoToProductEntity(ProductEntityDetailDto productEntityDetailDto);

    @Mapping(source = "productEntity.categoryEntity", target = "categoryEntityCreateDto")
    @Mapping(source = "productEntity.discountEntity", target = "discountEntityIndexDto")
    @Mapping(source = "productEntity.supplierEntity", target = "supplierEntityCreateDto")
    @Mapping(source = "productEntity.variationEntitySet", target = "variationEntityIndexDtoSet")
    ProductEntityDetailDto ProductEntityToProductEntityDetailDto(ProductEntity productEntity);
}
