package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.UserEntity.UserEntityCreateDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityIndexDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityUpdateDto;
import com.example.ecommerce_backend.mapper.UserEntity.UserEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<UserEntityIndexDto> create(@RequestBody UserEntityCreateDto userEntityCreateDto) {
        UserEntity userEntity = userServiceInterface.registerUser(userEntityCreateDto);
        UserEntityIndexDto userEntityIndexDto = userEntityIndexDtoMapper.UserEntityToUserEntityIndexDto(userEntity);
        return ResponseEntity.ok(userEntityIndexDto);
    }

    @GetMapping
    public ResponseEntity<Page<UserEntityIndexDto>> index(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserEntityIndexDto> userPage = userServiceInterface.findByCondition(pageable);
        return ResponseEntity.ok(userPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityIndexDto> findById(@PathVariable("id") int id) {
        UserEntity user = userServiceInterface.findUserById(id);
        UserEntityIndexDto userEntityIndexDto = userEntityIndexDtoMapper.UserEntityToUserEntityIndexDto(user);
        return ResponseEntity.ok(userEntityIndexDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        userServiceInterface.deleteUserById(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(UserEntityUpdateDto userEntityUpdateDto) {
        userServiceInterface.updateUser(userEntityUpdateDto);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/forgotPassword")
//    public ResponseEntity<Void> forgotPassword(@RequestBody UserEntityForgotPassword userEntityForgotPassword) {
//        UserEntity userEntity = userServiceInterface.registerUser(userEntityCreateDto);
//        UserEntityIndexDto userEntityIndexDto = userEntityIndexDtoMapper.UserEntityToUserEntityIndexDto(userEntity);
//        return ResponseEntity.ok().build();
//    }
}