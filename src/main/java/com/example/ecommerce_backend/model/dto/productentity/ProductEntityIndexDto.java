package com.example.ecommerce_backend.model.dto.productentity;

import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.model.dto.productcategoryentity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.model.dto.supplierentity.SupplierEntityCreateDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductEntityIndexDto {

  private int id;

  private String name;

  private String description;

  private CategoryEntityCreateDto categoryEntity;

  private SupplierEntityCreateDto supplierEntity;

  private DiscountEntityIndexDto discountEntity;
}
