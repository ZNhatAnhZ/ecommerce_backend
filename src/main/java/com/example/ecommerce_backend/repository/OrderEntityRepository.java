package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.OrderEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {

  Optional<OrderEntity> findByPaypalOrderId(String paypalOrderId);
}
