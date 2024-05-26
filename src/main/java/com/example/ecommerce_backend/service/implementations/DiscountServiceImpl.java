package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.DiscountEntity;
import com.example.ecommerce_backend.model.mapper.discountentity.DiscountEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.mapper.discountentity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.mapper.discountentity.DiscountEntityUpdateDtoMapper;
import com.example.ecommerce_backend.repository.DiscountEntityRepository;
import com.example.ecommerce_backend.service.interfaces.DiscountServiceInterface;
import java.util.List;
import java.util.Optional;
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
public class DiscountServiceImpl implements DiscountServiceInterface {

  private final DiscountEntityRepository discountEntityRepository;

  private final DiscountEntityCreateDtoMapper discountEntityCreateDtoMapper;

  private final DiscountEntityUpdateDtoMapper discountEntityUpdateDtoMapper;

  private final DiscountEntityIndexDtoMapper discountEntityIndexDtoMapper;

  public List<DiscountEntity> getAllDiscounts() {
    return discountEntityRepository.findAll();
  }

  public Page<DiscountEntityIndexDto> findByCondition(Pageable pageable) {
    return discountEntityRepository.findAll(pageable).map(discountEntityIndexDtoMapper::toDto);
  }

  public DiscountEntity getDiscountById(int id) {
    Optional<DiscountEntity> discountEntity = discountEntityRepository.findById(id);
    if (discountEntity.isPresent()) {
      return discountEntity.get();
    } else {
      throw new ResourceNotFoundException("Could not find discount with id " + id);
    }
  }

  public DiscountEntity createDiscount(DiscountEntityCreateDto discountCreateDto) {
    if (discountEntityRepository.existsByName(discountCreateDto.getName())) {
      throw new ResourceDuplicateException("this discount is already exist");
    } else {
      DiscountEntity discountEntity = discountEntityCreateDtoMapper.toEntity(discountCreateDto);
      return discountEntityRepository.save(discountEntity);
    }
  }

  public DiscountEntity updateDiscount(DiscountEntityUpdateDto discountUpdateDto) {
    if (discountEntityRepository.existsById(discountUpdateDto.getId())) {
      DiscountEntity discountEntity = discountEntityUpdateDtoMapper.toEntity(discountUpdateDto);
      return discountEntityRepository.save(discountEntity);
    } else {
      throw new ResourceNotFoundException(
          "Could not find discount with id " + discountUpdateDto.getId());
    }
  }

  public void deleteDiscount(int id) {
    if (discountEntityRepository.existsById(id)) {
      discountEntityRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("Could not find discount with id " + id);
    }
  }
}
