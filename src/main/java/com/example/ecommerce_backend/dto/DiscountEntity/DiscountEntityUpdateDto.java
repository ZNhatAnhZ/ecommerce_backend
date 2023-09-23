package com.example.ecommerce_backend.dto.DiscountEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiscountEntityUpdateDto {
    private int id;
    private String name;
    private String description;
    private String discountPercent;
    private String active;
}
