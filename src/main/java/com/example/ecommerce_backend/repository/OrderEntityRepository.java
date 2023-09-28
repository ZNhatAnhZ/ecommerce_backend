package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findByPaypalOrderId(String paypalOrderId);
}