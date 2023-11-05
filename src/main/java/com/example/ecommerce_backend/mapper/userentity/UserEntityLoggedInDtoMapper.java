package com.example.ecommerce_backend.mapper.userentity;

import com.example.ecommerce_backend.dto.userentity.UserEntityLoggedInDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityLoggedInDtoMapper {

	UserEntity toEntity(UserEntityLoggedInDto userEntityLoggedInDto);

	UserEntityLoggedInDto toDto(UserEntity userEntity);

}
