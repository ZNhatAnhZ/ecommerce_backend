package com.example.ecommerce_backend.dto.VariationEntity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VariationEntityIndexDto {
    private int id;
    private String attribute;
    private String value;
    private VariationEntityIndexDto parentVariationEntityIndexDto;
    private List<VariationEntityIndexDto> childVariationEntityIndexDtoList;
}
