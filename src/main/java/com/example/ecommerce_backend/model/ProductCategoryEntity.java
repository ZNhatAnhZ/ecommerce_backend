package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "product_category", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(region = "productCategory", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductCategoryEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

}
