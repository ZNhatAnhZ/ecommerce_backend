package com.example.ecommerce_backend.dto.productentity;

import com.example.ecommerce_backend.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.dto.productcategoryentity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.dto.supplierentity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.dto.variationentity.VariationEntityIndexDto;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

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
