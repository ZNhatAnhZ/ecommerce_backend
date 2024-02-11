package com.example.ecommerce_backend.mapper.cartitementiy;

import com.example.ecommerce_backend.dto.cartitementity.CartItemEntityIndexDto;
import com.example.ecommerce_backend.mapper.itementity.ItemEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.CartItemEntity;
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
