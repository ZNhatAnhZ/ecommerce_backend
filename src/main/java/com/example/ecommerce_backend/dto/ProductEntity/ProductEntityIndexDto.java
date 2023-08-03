package com.example.ecommerce_backend.dto.ProductEntity;

import com.example.ecommerce_backend.model.DiscountEntity;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import com.example.ecommerce_backend.model.SupplierEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductEntityIndexDto {
    private int id;
    private String name;
    private String description;
    private ProductCategoryEntity categoryEntity;
    private SupplierEntity supplierEntity;
    private DiscountEntity discountEntity;
}
