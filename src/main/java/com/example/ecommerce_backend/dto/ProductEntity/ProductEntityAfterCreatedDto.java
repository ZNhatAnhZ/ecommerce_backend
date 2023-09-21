package com.example.ecommerce_backend.dto.ProductEntity;

import com.example.ecommerce_backend.dto.ItemEntity.ItemEntityAfterCreatedDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductEntityAfterCreatedDto {
    private int id;
    private String name;
    private String description;
    private List<ItemEntityAfterCreatedDto> itemEntityAfterCreatedDtoList;
}
