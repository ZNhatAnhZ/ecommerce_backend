package com.example.ecommerce_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role", schema = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;
}
