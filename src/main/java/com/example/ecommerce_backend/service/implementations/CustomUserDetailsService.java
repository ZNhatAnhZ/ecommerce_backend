package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.model.CustomUserDetails;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userEntityRepository.findUserEntityByUsername(username);
        if (userEntity.isPresent()) {
            return new CustomUserDetails(userEntity.get());
        } else {
            throw new UsernameNotFoundException("User Not Found with -> username: " + username);
        }
    }
}
