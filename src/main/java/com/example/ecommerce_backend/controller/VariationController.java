package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.variationentity.VariationEntityFlatIndexDto;
import com.example.ecommerce_backend.dto.variationentity.VariationEntityUpdateInfoDto;
import com.example.ecommerce_backend.mapper.variationentity.VariationEntityFlatIndexDtoMapper;
import com.example.ecommerce_backend.service.interfaces.VariationServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/variations")
@RestController
@Slf4j
@RequiredArgsConstructor
public class VariationController {
  private final VariationServiceInterface variationServiceInterface;
  private final VariationEntityFlatIndexDtoMapper variationEntityFlatIndexDtoMapper;

  @GetMapping
  public ResponseEntity<Page<VariationEntityFlatIndexDto>> index(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(
        variationServiceInterface
            .findByCondition(pageable)
            .map(variationEntityFlatIndexDtoMapper::toDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<VariationEntityFlatIndexDto> getVariationById(@PathVariable int id) {
    return ResponseEntity.ok(
        variationEntityFlatIndexDtoMapper.toDto(variationServiceInterface.findVariationById(id)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<VariationEntityFlatIndexDto> updateVariation(
      @PathVariable int id,
      @RequestBody VariationEntityUpdateInfoDto variationEntityUpdateInfoDto) {
    variationEntityUpdateInfoDto.setId(id);
    return ResponseEntity.ok(
        variationEntityFlatIndexDtoMapper.toDto(
            variationServiceInterface.updateVariation(variationEntityUpdateInfoDto)));
  }
}
