package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.dto.productentity.*;
import com.example.ecommerce_backend.model.mapper.productentity.ProductEntityDetailDtoMapper;
import com.example.ecommerce_backend.model.mapper.productentity.ProductEntityIndexDtoMapper;
import com.example.ecommerce_backend.service.interfaces.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/products")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {

  private final ProductServiceInterface productServiceInterface;

  private final ProductEntityDetailDtoMapper productEntityDetailDtoMapper;

  private final ProductEntityIndexDtoMapper productEntityIndexDtoMapper;

  @PostMapping
  public ResponseEntity<ProductEntityAfterCreatedDto> create(
      @RequestBody ProductEntityCreateDto productEntityCreateDto) {
    return ResponseEntity.ok(productServiceInterface.createProduct(productEntityCreateDto));
  }

  @GetMapping
  public ResponseEntity<Page<ProductEntityIndexDto>> index(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(
        productServiceInterface.getAllProducts(pageable).map(productEntityIndexDtoMapper::toDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductEntityDetailDto> findById(@PathVariable("id") int id) {
    return ResponseEntity.ok(
        productEntityDetailDtoMapper.toDto(productServiceInterface.getProductById(id)));
  }

  @GetMapping("/search")
  public ResponseEntity<Page<ProductEntityIndexDto>> findByName(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
      @RequestParam String name) {
    return ResponseEntity.ok(
        productServiceInterface
            .searchProductByName(name, pageable)
            .map(productEntityIndexDtoMapper::toDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") int id) {
    productServiceInterface.deleteProduct(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductEntityIndexDto> update(
      @PathVariable("id") int id, @RequestBody ProductEntityUpdateDto productEntityUpdateDto) {
    productEntityUpdateDto.setId(id);
    return ResponseEntity.ok(
        productEntityIndexDtoMapper.toDto(
            productServiceInterface.updateProduct(productEntityUpdateDto)));
  }
}
