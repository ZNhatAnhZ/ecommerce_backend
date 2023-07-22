package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminEntityRepository extends JpaRepository<AdminEntity, Integer> {
}