package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityCreateDto;
import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityIndexDto;
import com.example.ecommerce_backend.model.VariationEntity;

import java.util.List;

public interface VariationServiceInterface {
    VariationEntityIndexDto createVariation(VariationEntityCreateDto variationEntityCreateDto);
    List<VariationEntity> createVariationInBulk(List<VariationEntityCreateDto> variationEntityCreateDtoList);
}
