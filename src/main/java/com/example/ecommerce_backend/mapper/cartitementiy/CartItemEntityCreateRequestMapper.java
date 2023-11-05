package com.example.ecommerce_backend.mapper.cartitementiy;

import com.example.ecommerce_backend.dto.cartitementity.CartItemEntityCreateRequestDto;
import com.example.ecommerce_backend.model.CartItemEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemEntityCreateRequestMapper {

	@Mapping(source = "itemEntityId", target = "itemEntity.id")
	@Mapping(source = "userId", target = "user.id")
	CartItemEntity toEntity(CartItemEntityCreateRequestDto cartItemEntityCreateRequestDto);

	@InheritInverseConfiguration(name = "toEntity")
	CartItemEntityCreateRequestDto toDto(CartItemEntity cartItemEntity);

	@InheritConfiguration(name = "toEntity")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	CartItemEntity partialUpdate(CartItemEntityCreateRequestDto cartItemEntityCreateRequestDto,
			@MappingTarget CartItemEntity cartItemEntity);

}