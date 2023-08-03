package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscountServiceInterface {
    List<DiscountEntity> getAllDiscounts();
    DiscountEntity getDiscountById(int id);
    DiscountEntity createDiscount(DiscountEntityCreateDto discountCreateDto);
    DiscountEntity updateDiscount(DiscountEntityUpdateDto discountUpdateDto);
    void deleteDiscount(int id);
    Page<DiscountEntityIndexDto> findByCondition(Pageable pageable);
}
