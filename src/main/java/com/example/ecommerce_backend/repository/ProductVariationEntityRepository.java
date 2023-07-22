package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.ProductVariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariationEntityRepository extends JpaRepository<ProductVariationEntity, Integer> {
}