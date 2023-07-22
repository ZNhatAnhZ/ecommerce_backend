package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressEntityRepository extends JpaRepository<UserAddressEntity, Integer> {
}