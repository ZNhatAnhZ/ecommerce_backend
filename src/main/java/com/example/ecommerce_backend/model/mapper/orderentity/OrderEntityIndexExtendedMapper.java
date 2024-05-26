package com.example.ecommerce_backend.model.mapper.orderentity;

import com.example.ecommerce_backend.model.dto.orderentity.OrderEntityIndexExtendedDto;
import com.example.ecommerce_backend.model.entity.OrderEntity;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderEntityIndexExtendedMapper {
  OrderEntity toEntity(OrderEntityIndexExtendedDto orderEntityIndexExtendedDto);

  @AfterMapping
  default void linkOrderItemEntityList(@MappingTarget OrderEntity orderEntity) {
    orderEntity
        .getOrderItemEntityList()
        .forEach(orderItemEntityList -> orderItemEntityList.setOrderEntity(orderEntity));
  }

  OrderEntityIndexExtendedDto toDto(OrderEntity orderEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  OrderEntity partialUpdate(
      OrderEntityIndexExtendedDto orderEntityIndexExtendedDto,
      @MappingTarget OrderEntity orderEntity);
}
