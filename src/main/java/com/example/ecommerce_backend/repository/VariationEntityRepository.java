package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.VariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariationEntityRepository extends JpaRepository<VariationEntity, Integer> {
}