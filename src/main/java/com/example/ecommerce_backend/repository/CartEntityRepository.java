package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntityRepository extends JpaRepository<CartEntity, Integer> {
}