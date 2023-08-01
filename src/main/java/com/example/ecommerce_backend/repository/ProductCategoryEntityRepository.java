package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryEntityRepository extends JpaRepository<ProductCategoryEntity, Integer> {
    Boolean existsByName(String name);
}