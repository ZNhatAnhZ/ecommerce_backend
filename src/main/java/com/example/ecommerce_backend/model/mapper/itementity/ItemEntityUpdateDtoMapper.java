package com.example.ecommerce_backend.model.mapper.itementity;

import com.example.ecommerce_backend.model.dto.itementity.ItemEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.ItemEntity;
import com.example.ecommerce_backend.model.mapper.productentity.ProductEntityIndexDtoMapper;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {ProductEntityIndexDtoMapper.class})
public interface ItemEntityUpdateDtoMapper {

  ItemEntity toEntity(ItemEntityUpdateDto itemEntityUpdateDto);

  ItemEntityUpdateDto toDto(ItemEntity itemEntity);
}
