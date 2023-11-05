package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.CartItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemEntityRepository extends JpaRepository<CartItemEntity, Integer> {

	Page<CartItemEntity> findByUser_Id(int id, Pageable pageable);

}