package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "variation", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "depend_on", referencedColumnName = "id")
    private VariationEntity parentVariationEntity;

    @OneToMany(mappedBy = "parentVariationEntity", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<VariationEntity> childVariationEntityList;

    public void addChildVariationEntity(VariationEntity variationEntity) {
        childVariationEntityList.add(variationEntity);
    }

    public static void updateParentVariationAndProductEntityForAllChildVariations(ProductEntity productEntity, VariationEntity parent, List<VariationEntity> children) {
        if (children != null) {
            children.forEach((child) -> {
                child.setProductEntity(productEntity);
                child.setParentVariationEntity(parent);
                updateParentVariationAndProductEntityForAllChildVariations(productEntity, child, child.getChildVariationEntityList());
            });
        }
    }
}
