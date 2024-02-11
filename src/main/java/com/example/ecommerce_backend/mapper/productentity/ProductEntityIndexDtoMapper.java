package com.example.ecommerce_backend.mapper.productentity;

import com.example.ecommerce_backend.dto.productentity.ProductEntityIndexDto;
import com.example.ecommerce_backend.mapper.categoryentity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.discountentity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.supplierentity.SupplierEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {
      CategoryEntityCreateDtoMapper.class,
      SupplierEntityCreateDtoMapper.class,
      DiscountEntityIndexDtoMapper.class
    })
public interface ProductEntityIndexDtoMapper {

  ProductEntity toEntity(ProductEntityIndexDto productEntityIndexDto);

  ProductEntityIndexDto toDto(ProductEntity productEntity);
}
