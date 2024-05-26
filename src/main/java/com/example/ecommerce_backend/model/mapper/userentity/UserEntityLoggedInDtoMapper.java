package com.example.ecommerce_backend.model.mapper.userentity;

import com.example.ecommerce_backend.model.dto.userentity.UserEntityLoggedInDto;
import com.example.ecommerce_backend.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityLoggedInDtoMapper {

  UserEntity toEntity(UserEntityLoggedInDto userEntityLoggedInDto);

  UserEntityLoggedInDto toDto(UserEntity userEntity);
}
