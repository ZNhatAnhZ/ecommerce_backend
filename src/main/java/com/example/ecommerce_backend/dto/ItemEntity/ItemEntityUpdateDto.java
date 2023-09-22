package com.example.ecommerce_backend.dto.ItemEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemEntityUpdateDto {
    private int id;
    private String price;
    private String stock;
    private boolean isDisabled;
}
