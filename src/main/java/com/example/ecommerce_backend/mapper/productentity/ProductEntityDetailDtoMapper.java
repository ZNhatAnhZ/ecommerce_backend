package com.example.ecommerce_backend.mapper.productentity;

import com.example.ecommerce_backend.dto.productentity.ProductEntityDetailDto;
import com.example.ecommerce_backend.mapper.categoryentity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.discountentity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.supplierentity.SupplierEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.variationentity.VariationEntityFlatIndexDtoMapper;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {
      VariationEntityFlatIndexDtoMapper.class,
      CategoryEntityCreateDtoMapper.class,
      SupplierEntityCreateDtoMapper.class,
      DiscountEntityIndexDtoMapper.class
    })
public interface ProductEntityDetailDtoMapper {

  ProductEntity toEntity(ProductEntityDetailDto productEntityDetailDto);

  @Mapping(source = "categoryEntity", target = "categoryEntity")
  @Mapping(source = "discountEntity", target = "discountEntity")
  @Mapping(source = "supplierEntity", target = "supplierEntity")
  @Mapping(source = "variationEntitySet", target = "variationEntity")
  ProductEntityDetailDto toDto(ProductEntity productEntity);
}
