package com.example.ecommerce_backend.dto.ProductEntity;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityCreateDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductEntityCreateDto {
    private String name;
    private String description;
    private int categoryId;
    private int supplierId;
    private int discountId;
    private List<VariationEntityCreateDto> variationEntityCreateDtoList;
}
