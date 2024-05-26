package com.example.ecommerce_backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paypal_business_account", schema = "ecommerce")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaypalBusinessAccountEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "email")
  private String email;

  @Column(name = "client_id")
  private String clientId;

  @Column(name = "client_secret")
  private String clientSecret;

  @Column(name = "is_enabled")
  private Boolean isEnable;
}
