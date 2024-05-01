package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityCreateDto;
import com.example.ecommerce_backend.dto.variationentity.VariationEntityUpdateInfoDto;
import com.example.ecommerce_backend.model.VariationEntity;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VariationServiceInterface {

  VariationEntity createVariation(VariationEntityCreateDto variationEntityCreateDto);

  List<VariationEntity> createVariationInBulk(
      List<VariationEntityCreateDto> variationEntityCreateDtoList);

  void deleteVariationById(int id);

  void deleteVariationInBatch(Set<VariationEntity> variationEntitySet);

  VariationEntity findVariationById(int id);

  Page<VariationEntity> findByCondition(Pageable pageable);

  VariationEntity updateVariation(VariationEntityUpdateInfoDto variationEntityUpdateInfoDto);
}
