package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityCreateDto;
import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.dto.DiscountEntity.DiscountEntityUpdateDto;
import com.example.ecommerce_backend.model.DiscountEntity;
import com.example.ecommerce_backend.service.interfaces.DiscountServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscountController {
    private final DiscountServiceInterface discountServiceInterface;

    @GetMapping
    public Page<DiscountEntityIndexDto> index(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return discountServiceInterface.findByCondition(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountEntity> getDiscountById(@PathVariable int id) {
        Optional<DiscountEntity> discount = discountServiceInterface.getDiscountById(id);
        return discount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiscountEntity> createDiscount(@RequestBody DiscountEntityCreateDto DiscountEntityCreateDto) {
        DiscountEntity createdDiscount = discountServiceInterface.createDiscount(DiscountEntityCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountEntity> updateDiscount(@PathVariable int id, @RequestBody DiscountEntityUpdateDto DiscountEntityUpdateDto) {
        DiscountEntityUpdateDto.setId(id);
        DiscountEntity updatedDiscount = discountServiceInterface.updateDiscount(DiscountEntityUpdateDto);
        return ResponseEntity.ok(updatedDiscount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable int id) {
        discountServiceInterface.deleteDiscount(id);
        return ResponseEntity.ok().build();
    }
}