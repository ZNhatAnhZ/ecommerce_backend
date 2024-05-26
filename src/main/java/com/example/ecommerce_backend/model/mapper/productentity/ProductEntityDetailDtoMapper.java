package com.example.ecommerce_backend.model.mapper.productentity;

import com.example.ecommerce_backend.model.dto.productentity.ProductEntityDetailDto;
import com.example.ecommerce_backend.model.entity.ProductEntity;
import com.example.ecommerce_backend.model.mapper.categoryentity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.mapper.discountentity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.mapper.supplierentity.SupplierEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.mapper.variationentity.VariationEntityFlatIndexDtoMapper;
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
