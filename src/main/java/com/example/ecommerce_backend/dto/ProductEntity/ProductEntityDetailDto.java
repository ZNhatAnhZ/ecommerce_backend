package com.example.ecommerce_backend.dto.ProductEntity;

import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityIndexDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import com.example.ecommerce_backend.model.SupplierEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
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
