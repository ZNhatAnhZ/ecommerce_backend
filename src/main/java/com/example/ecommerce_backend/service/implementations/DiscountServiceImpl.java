package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityIndexDto;
import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.DiscountEntity.DiscountEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.DiscountEntity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.DiscountEntity.DiscountEntityUpdateDtoMapper;
import com.example.ecommerce_backend.model.DiscountEntity;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import com.example.ecommerce_backend.repository.DiscountEntityRepository;
import com.example.ecommerce_backend.service.interfaces.DiscountServiceInterface;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
        return discountEntityRepository.findAll(pageable).map(discountEntityIndexDtoMapper::discountEntityToDiscountEntityIndexDto);
    }

    public Optional<DiscountEntity> getDiscountById(int id) {
        return discountEntityRepository.findById(id);
    }

    public DiscountEntity createDiscount(DiscountEntityCreateDto discountCreateDto) {
        if (discountEntityRepository.existsByName(discountCreateDto.getName())) {
            throw new ResourceDuplicateException("this discount is already exist");
        } else {
            DiscountEntity discountEntity = discountEntityCreateDtoMapper.DiscountCreateDtoToDiscountEntity(discountCreateDto);
            return discountEntityRepository.save(discountEntity);
        }
    }

    public DiscountEntity updateDiscount(DiscountEntityUpdateDto discountUpdateDto) {
        if (discountEntityRepository.existsById(discountUpdateDto.getId())) {
            DiscountEntity discountEntity = discountEntityUpdateDtoMapper.DiscountUpdateDtoToDiscountEntity(discountUpdateDto);
            return discountEntityRepository.save(discountEntity);
        } else {
            throw new ResourceNotFoundException("Could not find discount with id " + discountUpdateDto.getId());
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
