package com.example.ecommerce_backend.mapper;

import com.example.ecommerce_backend.dto.UserEntityIndexDto;
import com.example.ecommerce_backend.dto.UserEntityLoggedInDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityLoggedInDtoMapper {
    UserEntity UserEntityLoggedInDtoToUserEntity(UserEntityLoggedInDto userEntityLoggedInDto);

    UserEntityLoggedInDto UserEntityToUserEntityLoggedInDto(UserEntity userEntity);
}
