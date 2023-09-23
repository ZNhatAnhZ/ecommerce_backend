package com.example.ecommerce_backend.mapper.ItemEntity;

import com.example.ecommerce_backend.dto.ItemEntity.ItemEntityUpdateDto;
import com.example.ecommerce_backend.mapper.ProductEntity.ProductEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductEntityIndexDtoMapper.class})
public interface ItemEntityUpdateDtoMapper {
    ItemEntity toItemEntity(ItemEntityUpdateDto itemEntityUpdateDto);
    ItemEntityUpdateDto toItemEntityUpdateDto(ItemEntity itemEntity);
}
