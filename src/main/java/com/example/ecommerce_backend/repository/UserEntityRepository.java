package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
}
