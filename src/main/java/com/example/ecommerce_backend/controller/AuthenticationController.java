package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.UserEntity.UserEntityCreateDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityLoggedInDto;
import com.example.ecommerce_backend.mapper.UserEntity.UserEntityLoggedInDtoMapper;
import com.example.ecommerce_backend.model.CustomUserDetails;
import com.example.ecommerce_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/login")
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserEntityLoggedInDtoMapper userEntityLoggedInDtoMapper;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserEntityCreateDto userEntityCreateDto) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEntityCreateDto.getUsername(), userEntityCreateDto.getPassword()));

            if (authentication.isAuthenticated()) {
                CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
                UserEntityLoggedInDto userEntityLoggedInDto = userEntityLoggedInDtoMapper.UserEntityToUserEntityLoggedInDto(customUserDetails.getUserEntity());
                userEntityLoggedInDto.setJwt(jwtUtil.generateJwtToken(customUserDetails));
                return new ResponseEntity<>(userEntityLoggedInDto, HttpStatus.OK);
            }

        } catch (BadCredentialsException e) {
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        }
        return null;
    }
}
