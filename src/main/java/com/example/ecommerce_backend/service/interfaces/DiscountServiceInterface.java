package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.model.dto.discountentity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.DiscountEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountServiceInterface {

  List<DiscountEntity> getAllDiscounts();

  DiscountEntity getDiscountById(int id);

  DiscountEntity createDiscount(DiscountEntityCreateDto discountCreateDto);

  DiscountEntity updateDiscount(DiscountEntityUpdateDto discountUpdateDto);

  void deleteDiscount(int id);

  Page<DiscountEntityIndexDto> findByCondition(Pageable pageable);
}
