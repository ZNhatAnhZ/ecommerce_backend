package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityCreateDto;
import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityDetailDto;
import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityIndexDto;
import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityUpdateDto;
import com.example.ecommerce_backend.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductServiceInterface {
    void deleteProduct(int id);
    Page<ProductEntityIndexDto> getAllProducts(Pageable pageable);
    ProductEntityDetailDto getProductById(Integer id);
    ProductEntityIndexDto createProduct(ProductEntityCreateDto productEntityCreateDto);
    ProductEntityIndexDto updateProduct(ProductEntityUpdateDto productEntityUpdateDto);
}
