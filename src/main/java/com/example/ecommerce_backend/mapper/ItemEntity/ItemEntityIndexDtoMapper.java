package com.example.ecommerce_backend.mapper.ItemEntity;

import com.example.ecommerce_backend.dto.ItemEntity.ItemEntityIndexDto;
import com.example.ecommerce_backend.model.ItemEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ItemEntityIndexDtoMapper {
    ItemEntity toItemEntity(ItemEntityIndexDto itemEntityIndexDto);

    ItemEntityIndexDto toItemEntityIndexDto(ItemEntity itemEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ItemEntity partialUpdate(ItemEntityIndexDto itemEntityIndexDto, @MappingTarget ItemEntity itemEntity);
}
