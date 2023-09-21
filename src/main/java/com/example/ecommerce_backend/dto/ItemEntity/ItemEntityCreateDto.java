package com.example.ecommerce_backend.dto.ItemEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemEntityCreateDto {
    private int variationId;
    private String price;
    private int stock;
    private int productId;
}
