package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Integer> {

}
