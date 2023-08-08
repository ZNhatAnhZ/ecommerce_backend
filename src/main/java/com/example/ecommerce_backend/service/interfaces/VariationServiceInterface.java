package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityCreateDto;
import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityIndexDto;
import com.example.ecommerce_backend.model.VariationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface VariationServiceInterface {
    VariationEntityIndexDto createVariation(VariationEntityCreateDto variationEntityCreateDto);
    List<VariationEntity> createVariationInBulk(List<VariationEntityCreateDto> variationEntityCreateDtoList);
    void deleteVariationById(int id);
    void deleteVariationInBatch(Set<VariationEntity> variationEntitySet);
    VariationEntityIndexDto findVariationById(int id);
    Page<VariationEntityIndexDto> findByCondition(Pageable pageable);
}
