package com.example.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "user", schema = "ecommerce", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<CartEntity> cartsById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<OrderEntity> ordersById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserAddressEntity> userAddressesById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserPaymentEntity> userPaymentsById;
}
