package com.example.ecommerce_backend.dto.VariationEntity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VariationEntityUpdateDto {
    private String name;
    private String value;
    private List<VariationEntityUpdateDto> variationEntityUpdateDtoList;
}
