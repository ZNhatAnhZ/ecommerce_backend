package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.model.dto.userentity.UserEntityCreateDto;
import com.example.ecommerce_backend.model.dto.userentity.UserEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.UserEntity;
import com.example.ecommerce_backend.model.mapper.userentity.UserEntityCreateDtoMapper;
import com.example.ecommerce_backend.repository.UserEntityRepository;
import com.example.ecommerce_backend.service.interfaces.EmailServiceInterface;
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import java.util.Optional;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserServiceInterface {

  private final UserEntityRepository userEntityRepository;

  private final UserEntityCreateDtoMapper userEntityCreateDtoMapper;

  private final PasswordEncoder passwordEncoder;

  private final EmailServiceInterface emailServiceInterface;

  @Override
  public UserEntity registerUser(UserEntityCreateDto userDto) {
    if (Boolean.TRUE.equals(userEntityRepository.existsByUsername(userDto.getUsername()))) {
      throw new ResourceDuplicateException("username already exist");
    }

    UserEntity userEntity = userEntityCreateDtoMapper.toEntity(userDto);
    emailServiceInterface.sendWelcomeEmail(userEntity);

    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
    userEntity = userEntityRepository.save(userEntity);
    return userEntity;
  }

  @Override
  public void deleteUserById(int id) {
    if (userEntityRepository.existsById(id)) {
      userEntityRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("Could not find user with id " + id);
    }
  }

  @Override
  public UserEntity findUserById(int id) {
    Optional<UserEntity> userEntity = userEntityRepository.findById(id);
    if (userEntity.isPresent()) {
      return userEntity.get();
    } else {
      throw new ResourceNotFoundException("Could not find user with id " + id);
    }
  }

  @Override
  public Page<UserEntity> findByCondition(Pageable pageable) {
    return userEntityRepository.findAll(pageable);
  }

  @Override
  public UserEntity updateUser(UserEntityUpdateDto userEntityUpdateDto) {
    Optional<UserEntity> userEntity = userEntityRepository.findById(userEntityUpdateDto.getId());
    if (userEntity.isPresent()) {
      userEntity.get().setFirstName(userEntityUpdateDto.getFirstName());
      userEntity.get().setLastName(userEntityUpdateDto.getLastName());
      userEntity.get().setTelephone(userEntityUpdateDto.getTelephone());
      return userEntityRepository.save(userEntity.get());
    } else {
      throw new ResourceNotFoundException(
          "Could not find user with id " + userEntityUpdateDto.getId());
    }
  }
}
