package com.example.ecommerce_backend.mapper.userentity;

import com.example.ecommerce_backend.dto.userentity.UserEntityUpdateDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityUpdateDtoMapper {

  UserEntity toEntity(UserEntityUpdateDto userEntityUpdateDto);

  UserEntityUpdateDto toDto(UserEntity userEntity);
}
