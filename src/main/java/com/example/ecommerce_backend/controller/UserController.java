package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.userentity.UserEntityCreateDto;
import com.example.ecommerce_backend.dto.userentity.UserEntityIndexDto;
import com.example.ecommerce_backend.dto.userentity.UserEntityUpdateDto;
import com.example.ecommerce_backend.mapper.userentity.UserEntityIndexDtoMapper;
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserServiceInterface userServiceInterface;

  private final UserEntityIndexDtoMapper userEntityIndexDtoMapper;

  @PostMapping
  public ResponseEntity<UserEntityIndexDto> create(
      @RequestBody UserEntityCreateDto userEntityCreateDto) {
    return ResponseEntity.ok(
        userEntityIndexDtoMapper.toDto(userServiceInterface.registerUser(userEntityCreateDto)));
  }

  @GetMapping
  public ResponseEntity<Page<UserEntityIndexDto>> index(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(
        userServiceInterface.findByCondition(pageable).map(userEntityIndexDtoMapper::toDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntityIndexDto> findById(@PathVariable("id") int id) {
    return ResponseEntity.ok(userEntityIndexDtoMapper.toDto(userServiceInterface.findUserById(id)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") int id) {
    userServiceInterface.deleteUserById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserEntityIndexDto> update(
      @PathVariable("id") int id, UserEntityUpdateDto userEntityUpdateDto) {
    userEntityUpdateDto.setId(id);
    return ResponseEntity.ok(
        userEntityIndexDtoMapper.toDto(userServiceInterface.updateUser(userEntityUpdateDto)));
  }
}
