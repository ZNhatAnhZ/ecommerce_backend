package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.UserEntityCreateDto;
import com.example.ecommerce_backend.model.UserEntity;

import java.util.Optional;

public interface UserServiceInterface {
    Optional<UserEntity> registerUser(UserEntityCreateDto userDto);
}
