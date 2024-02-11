package com.example.ecommerce_backend.mapper.userentity;

import com.example.ecommerce_backend.dto.userentity.UserEntityIndexDto;
import com.example.ecommerce_backend.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityIndexDtoMapper {

  UserEntity toEntity(UserEntityIndexDto userEntityIndexDto);

  UserEntityIndexDto toDto(UserEntity userEntity);
}
