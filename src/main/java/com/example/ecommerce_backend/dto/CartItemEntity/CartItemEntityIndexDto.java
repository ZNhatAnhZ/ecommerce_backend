package com.example.ecommerce_backend.dto.CartItemEntity;

import com.example.ecommerce_backend.dto.ItemEntity.ItemEntityIndexDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.example.ecommerce_backend.model.CartItemEntity}
 */
@Data
@Builder
public class CartItemEntityIndexDto implements Serializable {
    private final int id;
    private final Integer quantity;
    private final String sku;
    private final ItemEntityIndexDto itemEntity;
}