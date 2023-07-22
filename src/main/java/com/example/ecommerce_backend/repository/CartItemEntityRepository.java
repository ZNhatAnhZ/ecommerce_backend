package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemEntityRepository extends JpaRepository<CartItemEntity, Integer> {
}