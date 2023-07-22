package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemEntityRepository extends JpaRepository<OrderItemEntity, Integer> {
}