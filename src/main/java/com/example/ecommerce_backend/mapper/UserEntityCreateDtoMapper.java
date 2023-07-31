package com.example.ecommerce_backend.mapper;

import com.example.ecommerce_backend.dto.UserEntityCreateDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityCreateDtoMapper {
    UserEntity UserEntityCreateDtoToUserEntity(UserEntityCreateDto userEntityCreateDto);
    UserEntityCreateDto UserEntityToUserEntityCreateDto(UserEntity userEntity);
}
