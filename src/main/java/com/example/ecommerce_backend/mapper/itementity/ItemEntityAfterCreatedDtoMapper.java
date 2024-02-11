package com.example.ecommerce_backend.mapper.itementity;

import com.example.ecommerce_backend.dto.itementity.ItemEntityAfterCreatedDto;
import com.example.ecommerce_backend.mapper.variationentity.VariationEntityForItemEntityMapper;
import com.example.ecommerce_backend.model.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {VariationEntityForItemEntityMapper.class})
public interface ItemEntityAfterCreatedDtoMapper {

  ItemEntity toEntity(ItemEntityAfterCreatedDto itemEntityAfterCreatedDto);

  ItemEntityAfterCreatedDto toDto(ItemEntity itemEntity);
}
