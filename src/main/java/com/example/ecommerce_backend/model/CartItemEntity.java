package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_item", schema = "ecommerce", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private CartEntity cartByCartId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productByProductId;
    @ManyToOne
    @JoinColumn(name = "product_variation", referencedColumnName = "id")
    private ProductVariationEntity productVariationByProductVariation;
}
