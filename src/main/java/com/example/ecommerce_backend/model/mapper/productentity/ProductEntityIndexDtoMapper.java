package com.example.ecommerce_backend.model.mapper.productentity;

import com.example.ecommerce_backend.model.dto.productentity.ProductEntityIndexDto;
import com.example.ecommerce_backend.model.entity.ProductEntity;
import com.example.ecommerce_backend.model.mapper.categoryentity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.mapper.discountentity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.mapper.supplierentity.SupplierEntityCreateDtoMapper;
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
