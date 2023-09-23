package com.example.ecommerce_backend.dto.VariationEntity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VariationEntityIndexDto {
    private int id;
    private String name;
    private String value;
    private List<VariationEntityIndexDto> childVariationEntityIndexDtoList;
}
