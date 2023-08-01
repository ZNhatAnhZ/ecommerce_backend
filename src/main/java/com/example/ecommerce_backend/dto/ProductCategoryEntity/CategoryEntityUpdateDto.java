package com.example.ecommerce_backend.dto.ProductCategoryEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryEntityUpdateDto {
    private int id;
    private String name;
    private String description;
}
