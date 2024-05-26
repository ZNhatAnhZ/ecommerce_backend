package com.example.ecommerce_backend.model.mapper.itementity;

import com.example.ecommerce_backend.model.dto.itementity.ItemEntityAfterCreatedDto;
import com.example.ecommerce_backend.model.entity.ItemEntity;
import com.example.ecommerce_backend.model.mapper.variationentity.VariationEntityForItemEntityMapper;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {VariationEntityForItemEntityMapper.class})
public interface ItemEntityAfterCreatedDtoMapper {

  ItemEntity toEntity(ItemEntityAfterCreatedDto itemEntityAfterCreatedDto);

  ItemEntityAfterCreatedDto toDto(ItemEntity itemEntity);
}
