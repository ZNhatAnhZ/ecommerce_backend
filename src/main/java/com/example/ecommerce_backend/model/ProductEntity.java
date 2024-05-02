package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "product", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @org.hibernate.annotations.Cache(region = "product", usage =
// CacheConcurrencyStrategy.READ_WRITE)
public class ProductEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private ProductCategoryEntity categoryEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "supplier_id", referencedColumnName = "id")
  private SupplierEntity supplierEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discount_id", referencedColumnName = "id")
  private DiscountEntity discountEntity;

  @OneToMany(
      mappedBy = "productEntity",
      cascade = {CascadeType.ALL},
      fetch = FetchType.EAGER)
  @OrderBy("id ASC")
  private Set<VariationEntity> variationEntitySet;
}
