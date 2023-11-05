package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "discount", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "discount_percent")
	private String discountPercent;

	@Column(name = "active")
	private String active;

	@OneToMany(mappedBy = "discountEntity")
	private List<OrderEntity> orderEntityList;

	@OneToMany(mappedBy = "discountEntity", fetch = FetchType.LAZY)
	private List<ProductEntity> productEntityList;

}
