package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "discount", schema = "ecommerce", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "discount_percent")
    private String discountPercent;
    @Basic
    @Column(name = "active")
    private String active;
    @OneToMany(mappedBy = "discountByDiscountId")
    private Collection<OrderEntity> ordersById;
    @OneToMany(mappedBy = "discountByDiscountId")
    private Collection<ProductEntity> productsById;
}
