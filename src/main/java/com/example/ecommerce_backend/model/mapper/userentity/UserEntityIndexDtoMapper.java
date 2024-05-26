package com.example.ecommerce_backend.model.mapper.userentity;

import com.example.ecommerce_backend.model.dto.userentity.UserEntityIndexDto;
import com.example.ecommerce_backend.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityIndexDtoMapper {

  UserEntity toEntity(UserEntityIndexDto userEntityIndexDto);

  UserEntityIndexDto toDto(UserEntity userEntity);
}
