package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.productentity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServiceInterface {

	void deleteProduct(int id);

	Page<ProductEntityIndexDto> getAllProducts(Pageable pageable);

	Page<ProductEntityIndexDto> searchProductByName(String name, Pageable pageable);

	ProductEntityDetailDto getProductById(Integer id);

	ProductEntityAfterCreatedDto createProduct(ProductEntityCreateDto productEntityCreateDto);

	ProductEntityIndexDto updateProduct(ProductEntityUpdateDto productEntityUpdateDto);

}
