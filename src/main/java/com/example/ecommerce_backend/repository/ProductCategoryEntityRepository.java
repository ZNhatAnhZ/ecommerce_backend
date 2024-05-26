package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryEntityRepository
    extends JpaRepository<ProductCategoryEntity, Integer> {

  Boolean existsByName(String name);
}
