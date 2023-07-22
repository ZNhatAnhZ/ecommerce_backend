package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "order", schema = "ecommerce", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private DiscountEntity discountByDiscountId;
    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<OrderItemEntity> orderItemsById;
}
