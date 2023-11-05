package com.example.ecommerce_backend.dto.productentity;

import com.example.ecommerce_backend.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.dto.productcategoryentity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.dto.supplierentity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.dto.variationentity.VariationEntityIndexDto;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductEntityDetailDto {

	private int id;

	private String name;

	private String description;

	private CategoryEntityCreateDto categoryEntityCreateDto;

	private SupplierEntityCreateDto supplierEntityCreateDto;

	private DiscountEntityIndexDto discountEntityIndexDto;

	private Set<VariationEntityIndexDto> variationEntityIndexDtoSet;

}
