package com.example.ecommerce_backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_item", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "sku")
  private String sku;

  @ManyToOne
  @JoinColumn(name = "cart_id", referencedColumnName = "id")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "item_id", referencedColumnName = "id")
  private ItemEntity itemEntity;
}
