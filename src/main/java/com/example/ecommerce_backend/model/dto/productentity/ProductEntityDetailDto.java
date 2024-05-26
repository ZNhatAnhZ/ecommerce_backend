package com.example.ecommerce_backend.model.dto.productentity;

import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.model.dto.productcategoryentity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.model.dto.supplierentity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityFlatIndexDto;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductEntityDetailDto {

  private int id;

  private String name;

  private String description;

  private CategoryEntityCreateDto categoryEntity;

  private SupplierEntityCreateDto supplierEntity;

  private DiscountEntityIndexDto discountEntity;

  private Set<VariationEntityFlatIndexDto> variationEntity;
}
