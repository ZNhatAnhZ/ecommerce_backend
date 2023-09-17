package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityIndexDto;
import com.example.ecommerce_backend.service.interfaces.VariationServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/variations")
@RestController
@Slf4j
@RequiredArgsConstructor
public class VariationController {
    private final VariationServiceInterface variationServiceInterface;

    @GetMapping
    public Page<VariationEntityIndexDto> index(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return variationServiceInterface.findByCondition(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariationEntityIndexDto> getVariationById(@PathVariable int id) {
        VariationEntityIndexDto variationEntityIndexDto = variationServiceInterface.findVariationById(id);
        return ResponseEntity.ok(variationEntityIndexDto);
    }
}
