package com.example.ecommerce_backend.model.mapper.userentity;

import com.example.ecommerce_backend.model.dto.userentity.UserEntityCreateDto;
import com.example.ecommerce_backend.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityCreateDtoMapper {

  UserEntity toEntity(UserEntityCreateDto userEntityCreateDto);

  UserEntityCreateDto toDto(UserEntity userEntity);
}
