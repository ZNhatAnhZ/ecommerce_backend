package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "product", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "productByProductId")
    private Collection<CartItemEntity> cartItemsById;

    @OneToMany(mappedBy = "productEntity")
    private Collection<OrderItemEntity> orderItemsById;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ProductCategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private SupplierEntity supplierEntity;

    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private DiscountEntity discountEntity;

    @OneToMany(mappedBy = "productEntity")
    private List<VariationEntity> variationEntityList;
}
