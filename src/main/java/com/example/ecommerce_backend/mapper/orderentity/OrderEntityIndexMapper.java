package com.example.ecommerce_backend.mapper.orderentity;

import com.example.ecommerce_backend.dto.orderentity.OrderEntityIndexDto;
import com.example.ecommerce_backend.model.OrderEntity;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderEntityIndexMapper {
  OrderEntity toEntity(OrderEntityIndexDto orderEntityIndexDto);

  OrderEntityIndexDto toDto(OrderEntity orderEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  OrderEntity partialUpdate(
      OrderEntityIndexDto orderEntityIndexDto, @MappingTarget OrderEntity orderEntity);
}
