package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.userentity.UserEntityCreateDto;
import com.example.ecommerce_backend.dto.userentity.UserEntityIndexDto;
import com.example.ecommerce_backend.dto.userentity.UserEntityUpdateDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserServiceInterface {

  UserEntity registerUser(UserEntityCreateDto userDto);

  void deleteUserById(int id);

  UserEntity findUserById(int id);

  Page<UserEntityIndexDto> findByCondition(Pageable pageable);

  UserEntity updateUser(UserEntityUpdateDto userEntityUpdateDto);
}
