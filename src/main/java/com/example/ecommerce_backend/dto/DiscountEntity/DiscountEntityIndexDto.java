package com.example.ecommerce_backend.dto.DiscountEntity;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityIndexDto;
import com.example.ecommerce_backend.model.ProductEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DiscountEntityIndexDto {
    private int id;
    private String name;
    private String description;
    private String discountPercent;
    private String active;
    private List<ProductEntityIndexDto> productEntityIndexDtoList;
}
