package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.UserPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentEntityRepository extends JpaRepository<UserPaymentEntity, Integer> {}
