package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.dto.userentity.UserEntityCreateDto;
import com.example.ecommerce_backend.model.dto.userentity.UserEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserServiceInterface {

  UserEntity registerUser(UserEntityCreateDto userDto);

  void deleteUserById(int id);

  UserEntity findUserById(int id);

  Page<UserEntity> findByCondition(Pageable pageable);

  UserEntity updateUser(UserEntityUpdateDto userEntityUpdateDto);
}
