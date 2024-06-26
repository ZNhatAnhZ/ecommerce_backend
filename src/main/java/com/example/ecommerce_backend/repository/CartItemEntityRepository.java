package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.entity.CartItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemEntityRepository extends JpaRepository<CartItemEntity, Integer> {

  Page<CartItemEntity> findByUser_Id(int id, Pageable pageable);
}
