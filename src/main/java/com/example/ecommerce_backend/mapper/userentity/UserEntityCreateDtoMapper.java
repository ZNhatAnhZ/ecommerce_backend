package com.example.ecommerce_backend.mapper.userentity;

import com.example.ecommerce_backend.dto.userentity.UserEntityCreateDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityCreateDtoMapper {

	UserEntity toEntity(UserEntityCreateDto userEntityCreateDto);

	UserEntityCreateDto toDto(UserEntity userEntity);

}
