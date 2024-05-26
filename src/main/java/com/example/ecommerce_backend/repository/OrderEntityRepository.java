package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.entity.OrderEntity;
import com.example.ecommerce_backend.util.constant.OrderStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {

  @EntityGraph(
      attributePaths = {
        "orderItemEntityList.itemEntity.productEntity",
        "orderItemEntityList.itemEntity.variationEntityList"
      })
  Optional<OrderEntity> findByPaypalOrderId(String paypalOrderId);

  @EntityGraph(
      attributePaths = {
        "orderItemEntityList.itemEntity.productEntity",
        "orderItemEntityList.itemEntity.variationEntityList"
      })
  List<OrderEntity> findAllByIdIn(List<Integer> orderIds);

  Page<OrderEntity> findAllByStatus(OrderStatus orderStatus, Pageable pageable);
}
