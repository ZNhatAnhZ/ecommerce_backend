package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityCreateDto;
import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityUpdateInfoDto;
import com.example.ecommerce_backend.model.entity.VariationEntity;
import com.example.ecommerce_backend.model.mapper.variationentity.VariationEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.mapper.variationentity.VariationEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.mapper.variationentity.VariationEntityUpdateDtoMapper;
import com.example.ecommerce_backend.model.mapper.variationentity.VariationEntityUpdateInfoDtoMapper;
import com.example.ecommerce_backend.repository.VariationEntityRepository;
import com.example.ecommerce_backend.service.interfaces.VariationServiceInterface;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Transactional
public class VariationServiceImpl implements VariationServiceInterface {

  private final VariationEntityCreateDtoMapper variationEntityCreateDtoMapper;

  private final VariationEntityIndexDtoMapper variationEntityIndexDtoMapper;

  private final VariationEntityUpdateDtoMapper variationEntityUpdateDtoMapper;

  private final VariationEntityUpdateInfoDtoMapper variationEntityUpdateInfoDtoMapper;

  private final VariationEntityRepository variationEntityRepository;

  @Override
  public VariationEntity createVariation(VariationEntityCreateDto variationEntityCreateDto) {
    VariationEntity variationEntity =
        variationEntityCreateDtoMapper.toEntity(variationEntityCreateDto);
    return variationEntityRepository.save(variationEntity);
  }

  @Override
  public List<VariationEntity> createVariationInBulk(
      List<VariationEntityCreateDto> variationEntityCreateDtoList) {
    List<VariationEntity> variationEntityList =
        variationEntityCreateDtoMapper.toEntityList(variationEntityCreateDtoList);
    Set<VariationEntity> variationEntitySet = new HashSet<>(variationEntityList);
    return variationEntityRepository.saveAll(variationEntitySet);
  }

  @Override
  public void deleteVariationById(int id) {
    if (variationEntityRepository.existsById(id)) {
      variationEntityRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("Could not find variation with id " + id);
    }
  }

  @Override
  public void deleteVariationInBatch(Set<VariationEntity> variationEntitySet) {
    variationEntityRepository.deleteAllInBatch(variationEntitySet);
  }

  @Override
  public VariationEntity findVariationById(int id) {
    Optional<VariationEntity> variationEntity = variationEntityRepository.findById(id);
    if (variationEntity.isPresent()) {
      return variationEntity.get();
    } else {
      throw new ResourceNotFoundException("Could not find variation with id " + id);
    }
  }

  @Override
  public Page<VariationEntity> findByCondition(Pageable pageable) {
    return variationEntityRepository.findAll(pageable);
  }

  @Override
  public VariationEntity updateVariation(
      VariationEntityUpdateInfoDto variationEntityUpdateInfoDto) {
    Optional<VariationEntity> variationEntity =
        variationEntityRepository.findById(variationEntityUpdateInfoDto.getId());
    if (variationEntity.isPresent()) {
      variationEntity.get().setName(variationEntityUpdateInfoDto.getName());
      variationEntity.get().setValue(variationEntityUpdateInfoDto.getValue());
      return variationEntityRepository.save(variationEntity.get());
    } else {
      throw new ResourceNotFoundException(
          "Could not find variation with id " + variationEntityUpdateInfoDto.getId());
    }
  }
}
