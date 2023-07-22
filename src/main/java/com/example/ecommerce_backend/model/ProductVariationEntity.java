package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "product_variation", schema = "ecommerce", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "product_id")
    private Integer productId;
    @Basic
    @Column(name = "variation_id")
    private Integer variationId;
    @Basic
    @Column(name = "sku")
    private String sku;
    @Basic
    @Column(name = "price")
    private String price;
    @OneToMany(mappedBy = "productVariationByProductVariation")
    private Collection<CartItemEntity> cartItemsById;
    @OneToMany(mappedBy = "productVariationByProductVariationId")
    private Collection<OrderItemEntity> orderItemsById;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productByProductId;
    @ManyToOne
    @JoinColumn(name = "variation_id", referencedColumnName = "id")
    private VariationEntity variationByVariationId;
}
