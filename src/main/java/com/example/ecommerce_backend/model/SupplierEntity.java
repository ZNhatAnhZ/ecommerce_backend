package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "supplier", schema = "ecommerce", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @OneToMany(mappedBy = "supplierBySupplierId")
    private Collection<ProductEntity> productsById;
}
