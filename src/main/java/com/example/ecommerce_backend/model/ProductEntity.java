package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "product", schema = "ecommerce", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(mappedBy = "productByProductId")
    private Collection<OrderItemEntity> orderItemsById;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ProductCategoryEntity productCategoryByCategoryId;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private SupplierEntity supplierBySupplierId;
    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private DiscountEntity discountByDiscountId;
    @OneToMany(mappedBy = "productByProductId")
    private Collection<ProductVariationEntity> productVariationsById;
}
