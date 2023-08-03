package com.example.ecommerce_backend.dto.VariationEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariationEntityCreateDto {
    private String attribute;
    private String value;
    private String price;
    private int stock;
    private int productId;
    private int parentVariationEntityId;
}
