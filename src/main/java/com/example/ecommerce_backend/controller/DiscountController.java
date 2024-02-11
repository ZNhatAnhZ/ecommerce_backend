package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.discountentity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.dto.discountentity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.mapper.discountentity.DiscountEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.DiscountEntity;
import com.example.ecommerce_backend.service.interfaces.DiscountServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
@Slf4j
@RequiredArgsConstructor
public class DiscountController {

  private final DiscountServiceInterface discountServiceInterface;

  private final DiscountEntityIndexDtoMapper discountEntityIndexDtoMapper;

  @GetMapping
  public Page<DiscountEntityIndexDto> index(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    return discountServiceInterface.findByCondition(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DiscountEntityIndexDto> getDiscountById(@PathVariable int id) {
    DiscountEntity discount = discountServiceInterface.getDiscountById(id);
    DiscountEntityIndexDto discountEntityIndexDto = discountEntityIndexDtoMapper.toDto(discount);
    return ResponseEntity.ok(discountEntityIndexDto);
  }

  @PostMapping
  public ResponseEntity<DiscountEntity> createDiscount(
      @RequestBody DiscountEntityCreateDto discountEntityCreateDto) {
    DiscountEntity createdDiscount =
        discountServiceInterface.createDiscount(discountEntityCreateDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscount);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DiscountEntity> updateDiscount(
      @PathVariable int id, @RequestBody DiscountEntityUpdateDto discountEntityUpdateDto) {
    discountEntityUpdateDto.setId(id);
    DiscountEntity updatedDiscount =
        discountServiceInterface.updateDiscount(discountEntityUpdateDto);
    return ResponseEntity.ok(updatedDiscount);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDiscount(@PathVariable int id) {
    discountServiceInterface.deleteDiscount(id);
    return ResponseEntity.ok().build();
  }
}
