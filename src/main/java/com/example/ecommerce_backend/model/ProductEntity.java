package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@org.hibernate.annotations.Cache(region = "product", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    @Column(name = "is_completed")
//    private int isCompleted;

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

    @OneToMany(mappedBy = "productEntity", cascade = {CascadeType.ALL})
    private Set<VariationEntity> variationEntitySet;
}
