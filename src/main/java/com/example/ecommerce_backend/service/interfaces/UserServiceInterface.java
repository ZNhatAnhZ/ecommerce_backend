package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.UserEntity.UserEntityCreateDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityIndexDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityUpdateDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserServiceInterface {
    UserEntity registerUser(UserEntityCreateDto userDto);
    void deleteUserById(int id);
    UserEntity findUserById(int id);
    Page<UserEntityIndexDto> findByCondition(Pageable pageable);
    UserEntity updateUser(UserEntityUpdateDto userEntityUpdateDto);
}
