package com.example.ecommerce_backend.mapper.productentity;

import com.example.ecommerce_backend.dto.productentity.ProductEntityDetailDto;
import com.example.ecommerce_backend.mapper.categoryentity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.discountentity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.supplierentity.SupplierEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.variationentity.VariationEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { VariationEntityIndexDtoMapper.class, CategoryEntityCreateDtoMapper.class,
		SupplierEntityCreateDtoMapper.class, DiscountEntityIndexDtoMapper.class })
public interface ProductEntityDetailDtoMapper {

	ProductEntity toEntity(ProductEntityDetailDto productEntityDetailDto);

	@Mapping(source = "productEntity.categoryEntity", target = "categoryEntityCreateDto")
	@Mapping(source = "productEntity.discountEntity", target = "discountEntityIndexDto")
	@Mapping(source = "productEntity.supplierEntity", target = "supplierEntityCreateDto")
	@Mapping(source = "productEntity.variationEntitySet", target = "variationEntityIndexDtoSet")
	ProductEntityDetailDto toDto(ProductEntity productEntity);

}
