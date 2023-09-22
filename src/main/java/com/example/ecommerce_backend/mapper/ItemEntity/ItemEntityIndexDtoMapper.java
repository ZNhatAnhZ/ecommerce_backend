package com.example.ecommerce_backend.mapper.ItemEntity;

import com.example.ecommerce_backend.dto.ItemEntity.ItemEntityIndexDto;
import com.example.ecommerce_backend.model.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemEntityIndexDtoMapper {
    ItemEntity toItemEntity(ItemEntityIndexDto itemEntityIndexDto);
    ItemEntityIndexDto toItemEntityIndexDto(ItemEntity itemEntity);
}
