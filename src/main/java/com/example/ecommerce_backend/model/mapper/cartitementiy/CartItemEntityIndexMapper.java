package com.example.ecommerce_backend.model.mapper.cartitementiy;

import com.example.ecommerce_backend.model.dto.cartitementity.CartItemEntityIndexDto;
import com.example.ecommerce_backend.model.entity.CartItemEntity;
import com.example.ecommerce_backend.model.mapper.itementity.ItemEntityIndexDtoMapper;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {ItemEntityIndexDtoMapper.class})
public interface CartItemEntityIndexMapper {

  CartItemEntity toEntity(CartItemEntityIndexDto cartItemEntityIndexDto);

  CartItemEntityIndexDto toDto(CartItemEntity cartItemEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  CartItemEntity partialUpdate(
      CartItemEntityIndexDto cartItemEntityIndexDto, @MappingTarget CartItemEntity cartItemEntity);
}
