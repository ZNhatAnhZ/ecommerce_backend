package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "item", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private String price;

    @Column(name = "stock")
    private String stock;

    @OneToMany(mappedBy = "itemEntity")
    private Collection<CartItemEntity> cartItemsById;

    @OneToMany(mappedBy = "itemEntity")
    private Collection<OrderItemEntity> orderItemsById;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "item_variation", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "variation_id"))
    private List<VariationEntity> variationEntityList;
}
