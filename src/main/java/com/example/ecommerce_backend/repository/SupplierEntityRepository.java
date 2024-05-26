package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierEntityRepository extends JpaRepository<SupplierEntity, Integer> {

  Boolean existsByName(String name);
}
