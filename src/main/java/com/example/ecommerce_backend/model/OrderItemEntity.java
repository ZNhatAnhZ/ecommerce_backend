package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;

@Entity
@Table(name = "order_item", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price")
  private String totalPrice;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private OrderEntity orderEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", referencedColumnName = "id")
  private ItemEntity itemEntity;
}
