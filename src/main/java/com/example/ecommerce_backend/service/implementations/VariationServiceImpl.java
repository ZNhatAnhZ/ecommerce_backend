package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityCreateDto;
import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityIndexDto;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityUpdateDtoMapper;
import com.example.ecommerce_backend.model.VariationEntity;
import com.example.ecommerce_backend.repository.VariationEntityRepository;
import com.example.ecommerce_backend.service.interfaces.VariationServiceInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Transactional
public class VariationServiceImpl implements VariationServiceInterface {
    private final VariationEntityCreateDtoMapper variationEntityCreateDtoMapper;
    private final VariationEntityIndexDtoMapper variationEntityIndexDtoMapper;
    private final VariationEntityUpdateDtoMapper variationEntityUpdateDtoMapper;
    private final VariationEntityRepository variationEntityRepository;
    public VariationEntityIndexDto createVariation(VariationEntityCreateDto variationEntityCreateDto) {
        VariationEntity variationEntity = variationEntityCreateDtoMapper.VariationEntityCreateDtoToVariationEntity(variationEntityCreateDto);
        return variationEntityIndexDtoMapper.VariationEntityToVariationEntityIndexDto(variationEntityRepository.save(variationEntity));
    }

    public List<VariationEntity> createVariationInBulk(List<VariationEntityCreateDto> variationEntityCreateDtoList) {
        List<VariationEntity> variationEntityList = variationEntityCreateDtoMapper.VariationEntityCreateDtoListToVariationEntityList(variationEntityCreateDtoList);
        Set<VariationEntity> variationEntitySet = new HashSet<>(variationEntityList);
        return variationEntityRepository.saveAll(variationEntitySet);
    }

    public void deleteVariationById(int id) {
        if (variationEntityRepository.existsById(id)) {
            variationEntityRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Could not find variation with id " + id);
        }
    }

    public void deleteVariationInBatch(Set<VariationEntity> variationEntitySet) {
        variationEntityRepository.deleteAllInBatch(variationEntitySet);
    }

    public VariationEntityIndexDto findVariationById(int id) {
        Optional<VariationEntity> variationEntity = variationEntityRepository.findById(id);
        if (variationEntity.isPresent()) {
            return variationEntityIndexDtoMapper.VariationEntityToVariationEntityIndexDto(variationEntity.get());
        } else {
            throw new ResourceNotFoundException("Could not find variation with id " + id);
        }
    }

    public Page<VariationEntityIndexDto> findByCondition(Pageable pageable) {
        return variationEntityRepository.findAll(pageable).map(variationEntityIndexDtoMapper::VariationEntityToVariationEntityIndexDto);
    }
}
