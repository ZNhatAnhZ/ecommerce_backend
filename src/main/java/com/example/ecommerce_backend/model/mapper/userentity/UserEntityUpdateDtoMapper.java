package com.example.ecommerce_backend.model.mapper.userentity;

import com.example.ecommerce_backend.model.dto.userentity.UserEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityUpdateDtoMapper {

  UserEntity toEntity(UserEntityUpdateDto userEntityUpdateDto);

  UserEntityUpdateDto toDto(UserEntity userEntity);
}
