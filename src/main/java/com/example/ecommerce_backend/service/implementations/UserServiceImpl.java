package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.UserEntityCreateDto;
import com.example.ecommerce_backend.dto.UserEntityIndexDto;
import com.example.ecommerce_backend.mapper.UserEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.UserEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.repository.UserEntityRepository;
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserServiceInterface {
    private final UserEntityRepository userEntityRepository;
    private final UserEntityCreateDtoMapper userEntityCreateDtoMapper;
    private final UserEntityIndexDtoMapper userEntityIndexDtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserEntity> registerUser(UserEntityCreateDto userDto) {
        UserEntity userEntity = userEntityCreateDtoMapper.UserEntityCreateDtoToUserEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity = userEntityRepository.save(userEntity);
        return Optional.of(userEntity);
    }

    public void deleteUserById(int id) {
        userEntityRepository.deleteById(id);
    }

    public Optional<UserEntity> findUserById(int id) {
        return userEntityRepository.findById(id);
    }

    public Page<UserEntityIndexDto> findByCondition(Pageable pageable) {
        return userEntityRepository.findAll(pageable).map(userEntityIndexDtoMapper::UserEntityToUserEntityIndexDto);
    }

//    public UserEntityDto update(UserEntityDto userDto, int id) {
//        UserEntityDto data = findById(id);
//        User entity = userMapper.toEntity(userDto);
//        BeanUtil.copyProperties(data, entity);
//        return save(userMapper.toDto(entity));
//    }
}
