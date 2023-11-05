package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findUserEntityByUsername(String username);

	Boolean existsByUsername(String username);

}
