package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountEntityRepository extends JpaRepository<DiscountEntity, Integer> {
    boolean existsByName(String name);
}