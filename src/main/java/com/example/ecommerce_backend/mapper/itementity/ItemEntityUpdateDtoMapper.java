package com.example.ecommerce_backend.mapper.itementity;

import com.example.ecommerce_backend.dto.itementity.ItemEntityUpdateDto;
import com.example.ecommerce_backend.mapper.productentity.ProductEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {ProductEntityIndexDtoMapper.class})
public interface ItemEntityUpdateDtoMapper {

  ItemEntity toEntity(ItemEntityUpdateDto itemEntityUpdateDto);

  ItemEntityUpdateDto toDto(ItemEntity itemEntity);
}
