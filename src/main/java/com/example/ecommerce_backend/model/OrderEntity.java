package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "`order`", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "sub_total")
  private String subTotal;

  @Column(name = "grand_total")
  private String grandTotal;

  @Column(name = "status")
  private String status;

  @Column(name = "shipping")
  private String shipping;

  @Column(name = "paypal_order_id")
  private String paypalOrderId;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Column(name = "email")
  private String email;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity userEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discount_id", referencedColumnName = "id")
  private DiscountEntity discountEntity;

  @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<OrderItemEntity> orderItemEntityList;
}
