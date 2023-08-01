package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "product_variation", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private String price;

    @OneToMany(mappedBy = "productVariationByProductVariation")
    private Collection<CartItemEntity> cartItemsById;

    @OneToMany(mappedBy = "productVariationEntity")
    private Collection<OrderItemEntity> orderItemsById;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "variation_id", referencedColumnName = "id")
    private VariationEntity variationEntity;
}
