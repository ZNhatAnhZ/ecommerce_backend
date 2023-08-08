package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityCreateDto;
import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityIndexDto;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.VariationEntity;
import com.example.ecommerce_backend.repository.VariationEntityRepository;
import com.example.ecommerce_backend.service.interfaces.VariationServiceInterface;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class VariationServiceImpl implements VariationServiceInterface {
    private final VariationEntityCreateDtoMapper variationEntityCreateDtoMapper;
    private final VariationEntityIndexDtoMapper variationEntityIndexDtoMapper;
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
}
