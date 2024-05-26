package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.dto.productentity.ProductEntityAfterCreatedDto;
import com.example.ecommerce_backend.model.dto.productentity.ProductEntityCreateDto;
import com.example.ecommerce_backend.model.dto.productentity.ProductEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServiceInterface {

  void deleteProduct(int id);

  Page<ProductEntity> getAllProducts(Pageable pageable);

  Page<ProductEntity> searchProductByName(String name, Pageable pageable);

  ProductEntity getProductById(Integer id);

  ProductEntityAfterCreatedDto createProduct(ProductEntityCreateDto productEntityCreateDto);

  ProductEntity updateProduct(ProductEntityUpdateDto productEntityUpdateDto);
}
