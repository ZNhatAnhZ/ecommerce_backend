package com.example.ecommerce_backend.mapper.UserEntity;

import com.example.ecommerce_backend.dto.UserEntity.UserEntityUpdateDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityUpdateDtoMapper {
    UserEntity UserEntityUpdateDtoToUserEntity(UserEntityUpdateDto userEntityUpdateDto);

    UserEntityUpdateDto UserEntityToUserEntityUpdateDto(UserEntity userEntity);
}
