package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.entity.PaypalBusinessAccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaypalBusinessAccountEntityRepository
    extends JpaRepository<PaypalBusinessAccountEntity, Integer> {
  Optional<PaypalBusinessAccountEntity> findFirstByOrderByIdAsc();

  boolean existsByEmail(String email);

  boolean existsByClientId(String clientId);
}
