package com.example.ecommerce_backend.mapper.OrderEntity;

import com.example.ecommerce_backend.dto.OrderEntity.OrderItemCreateDto;
import com.example.ecommerce_backend.model.OrderItemEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemCreateMapper {
    @Mapping(source = "itemEntitySku", target = "itemEntity.sku")
    @Mapping(source = "itemEntityId", target = "itemEntity.id")
    OrderItemEntity toEntity(OrderItemCreateDto orderItemCreateDto);

    @InheritInverseConfiguration(name = "toEntity")
    OrderItemCreateDto toDto(OrderItemEntity orderItemEntity);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItemEntity partialUpdate(OrderItemCreateDto orderItemCreateDto, @MappingTarget OrderItemEntity orderItemEntity);
}