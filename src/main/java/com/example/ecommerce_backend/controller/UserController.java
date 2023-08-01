package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.UserEntity.UserEntityCreateDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityIndexDto;
import com.example.ecommerce_backend.mapper.UserEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.service.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/users")
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserServiceImpl userService;
    private final UserEntityIndexDtoMapper userEntityIndexDtoMapper;

    @PostMapping
    public ResponseEntity<UserEntityIndexDto> create(@RequestBody UserEntityCreateDto userEntityCreateDto) {
        Optional<UserEntity> userEntity = userService.registerUser(userEntityCreateDto);
        if (userEntity.isPresent()) {
            UserEntityIndexDto userEntityIndexDto = userEntityIndexDtoMapper.UserEntityToUserEntityIndexDto(userEntity.get());
            return ResponseEntity.ok(userEntityIndexDto);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<UserEntityIndexDto>> index(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserEntityIndexDto> userPage = userService.findByCondition(pageable);
        return ResponseEntity.ok(userPage);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserEntityDto> findById(@PathVariable("id") int id) {
//        UserEntityDto user = userService.findById(id);
//        return ResponseEntity.ok(user);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
//        Optional.ofNullable(userService.findById(id)).orElseThrow(() -> {
//            log.error("Unable to delete non-existent data！");
//            return new ResourceNotFoundException();
//        });
//        userService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/page-query")
//    public ResponseEntity<Page<UserEntityDto>> pageQuery(UserEntityDto UserEntityDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
//        Page<UserEntityDto> userPage = userService.findByCondition(UserEntityDto, pageable);
//        return ResponseEntity.ok(userPage);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> update(@RequestBody @Validated UserEntityDto UserEntityDto, @PathVariable("id") int id) {
//        userService.update(UserEntityDto, id);
//        return ResponseEntity.ok().build();
//    }
}