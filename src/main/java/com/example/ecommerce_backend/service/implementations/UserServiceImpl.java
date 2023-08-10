package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.UserEntity.UserEntityCreateDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityIndexDto;
import com.example.ecommerce_backend.dto.UserEntity.UserEntityUpdateDto;
import com.example.ecommerce_backend.exception.EmailSendingException;
import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.UserEntity.UserEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.UserEntity.UserEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.UserEntity.UserEntityUpdateDtoMapper;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.repository.UserEntityRepository;
import com.example.ecommerce_backend.service.interfaces.EmailServiceInterface;
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.io.Files;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.antlr.v4.runtime.misc.Utils.readFile;

@Service
@Getter
@Setter
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class UserServiceImpl implements UserServiceInterface {
    private final UserEntityRepository userEntityRepository;
    private final UserEntityCreateDtoMapper userEntityCreateDtoMapper;
    private final UserEntityIndexDtoMapper userEntityIndexDtoMapper;
    private final UserEntityUpdateDtoMapper userEntityUpdateDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceInterface emailServiceInterface;

    @Override
    public UserEntity registerUser(UserEntityCreateDto userDto) {
        if (!userEntityRepository.existsByUsername(userDto.getUsername())) {
            UserEntity userEntity = userEntityCreateDtoMapper.UserEntityCreateDtoToUserEntity(userDto);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity = userEntityRepository.save(userEntity);

            sendWelcomeEmail(userEntity);

            return userEntity;
        } else {
            throw new ResourceDuplicateException("username already exist");
        }
    }

    public void deleteUserById(int id) {
        if (userEntityRepository.existsById(id)) {
            userEntityRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Could not find user with id " + id);
        }
    }

    public UserEntity findUserById(int id) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(id);
        if (userEntity.isPresent()) {
            return userEntity.get();
        } else {
            throw new ResourceNotFoundException("Could not find user with id " + id);
        }
    }

    public Page<UserEntityIndexDto> findByCondition(Pageable pageable) {
        return userEntityRepository.findAll(pageable).map(userEntityIndexDtoMapper::UserEntityToUserEntityIndexDto);
    }

    public UserEntity updateUser(UserEntityUpdateDto userEntityUpdateDto) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(userEntityUpdateDto.getId());
        if (userEntity.isPresent()) {
            userEntity.get().setFirstName(userEntityUpdateDto.getFirstName());
            userEntity.get().setLastName(userEntityUpdateDto.getLastName());
            userEntity.get().setTelephone(userEntityUpdateDto.getTelephone());
            return userEntityRepository.save(userEntity.get());
        } else {
            throw new ResourceNotFoundException("Could not find user with id " + userEntityUpdateDto.getId());
        }
    }

    private void sendWelcomeEmail(UserEntity userEntity) {
        try {
            File file = ResourceUtils.getFile("classpath:welcome_email.html");
            String htmlTemplate = Files.asCharSource(file, StandardCharsets.UTF_8).read();

            // Replace placeholders in the HTML template with dynamic values
            htmlTemplate = htmlTemplate.replace("[Customer's Name]", userEntity.getFirstName());
            emailServiceInterface.sendEmailFromTemplate(userEntity.getEmail(), "User successfully registered to ecommerce app", htmlTemplate);

        } catch (IOException | MessagingException ex) {
            log.error(ex.toString());
            throw new EmailSendingException(ex.toString());
        }
    }
}
