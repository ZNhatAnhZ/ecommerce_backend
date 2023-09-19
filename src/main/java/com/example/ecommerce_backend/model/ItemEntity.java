package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "item", schema = "ecommerce")
@Getter
@Setter
@Builder
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

    @Column(name = "is_disabled")
    private boolean isDisabled;

    @OneToMany(mappedBy = "itemEntity", fetch = FetchType.LAZY)
    private List<CartItemEntity> cartItemsById;

    @OneToMany(mappedBy = "itemEntity", fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItemsById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_variation", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "variation_id"))
    private Set<VariationEntity> variationEntityList;
}
