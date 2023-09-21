package com.example.ecommerce_backend.mapper.ItemEntity;

import com.example.ecommerce_backend.dto.ItemEntity.ItemEntityAfterCreatedDto;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityForItemEntityMapper;
import com.example.ecommerce_backend.model.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VariationEntityForItemEntityMapper.class})
public interface ItemEntityAfterCreatedDtoMapper {
    ItemEntity toItemEntity(ItemEntityAfterCreatedDto itemEntityAfterCreatedDto);
    ItemEntityAfterCreatedDto toItemEntityAfterCreatedDto(ItemEntity itemEntity);
}
