package com.example.ecommerce_backend.dto.ProductVariationEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductVariationCreateDto {
    private String price;
    private String attribute;
    private String value;
}
