package com.example.ecommerce_backend.mapper.ProductEntity;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityIndexDto;
import com.example.ecommerce_backend.mapper.CategoryEntity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.DiscountEntity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.SupplierEntity.SupplierEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryEntityCreateDtoMapper.class,
        SupplierEntityCreateDtoMapper.class})
public interface ProductEntityIndexDtoMapper {
    ProductEntity ProductEntityIndexDtoToProductEntity(ProductEntityIndexDto productEntityIndexDto);
//    @Mapping(source = "productEntity.discountEntity", target = "discountEntityIndexDto")
    @Mapping(source = "productEntity.supplierEntity", target = "supplierEntityCreateDto")
    @Mapping(source = "productEntity.categoryEntity", target = "categoryEntityCreateDto")
    ProductEntityIndexDto ProductEntityToProductEntityIndexDto(ProductEntity productEntity);
}
