package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.entity.DiscountEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountEntityRepository extends JpaRepository<DiscountEntity, Integer> {

  boolean existsByName(String name);

  @EntityGraph(
      attributePaths = {"productEntityList.supplierEntity"},
      type = EntityGraph.EntityGraphType.FETCH)
  @Override
  @NotNull
  Page<DiscountEntity> findAll(@NotNull Pageable pageable);
}
