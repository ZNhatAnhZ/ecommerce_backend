package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
}