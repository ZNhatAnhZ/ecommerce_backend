package com.example.ecommerce_backend.model.mapper.itementity;

import com.example.ecommerce_backend.model.dto.itementity.ItemEntityIndexDto;
import com.example.ecommerce_backend.model.entity.ItemEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ItemEntityIndexDtoMapper {

  ItemEntity toEntity(ItemEntityIndexDto itemEntityIndexDto);

  ItemEntityIndexDto toDto(ItemEntity itemEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  ItemEntity partialUpdate(
      ItemEntityIndexDto itemEntityIndexDto, @MappingTarget ItemEntity itemEntity);
}
