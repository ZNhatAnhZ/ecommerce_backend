package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.productentity.*;
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

	@PostMapping
	public ResponseEntity<ProductEntityAfterCreatedDto> create(
			@RequestBody ProductEntityCreateDto productEntityCreateDto) {
		return ResponseEntity.ok(productServiceInterface.createProduct(productEntityCreateDto));
	}

	@GetMapping
	public ResponseEntity<Page<ProductEntityIndexDto>> index(
			@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<ProductEntityIndexDto> productEntityIndexDtoPage = productServiceInterface.getAllProducts(pageable);
		return ResponseEntity.ok(productEntityIndexDtoPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductEntityDetailDto> findById(@PathVariable("id") int id) {
		ProductEntityDetailDto productEntityDetailDto = productServiceInterface.getProductById(id);
		return ResponseEntity.ok(productEntityDetailDto);
	}

	@GetMapping("/search")
	public ResponseEntity<Page<ProductEntityIndexDto>> findByName(
			@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam String name) {
		Page<ProductEntityIndexDto> productEntityIndexDtoPage = productServiceInterface.searchProductByName(name,
				pageable);
		return ResponseEntity.ok(productEntityIndexDtoPage);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		productServiceInterface.deleteProduct(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductEntityIndexDto> update(@PathVariable("id") int id,
			@RequestBody ProductEntityUpdateDto productEntityUpdateDto) {
		productEntityUpdateDto.setId(id);
		return ResponseEntity.ok(productServiceInterface.updateProduct(productEntityUpdateDto));
	}

}
