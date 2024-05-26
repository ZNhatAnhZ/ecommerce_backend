package com.example.ecommerce_backend.model.mapper.paypalbusinessaccount;

import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountIndexDto;
import com.example.ecommerce_backend.model.entity.PaypalBusinessAccountEntity;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaypalBusinessAccountIndexMapper {
  PaypalBusinessAccountEntity toEntity(PaypalBusinessAccountIndexDto paypalBusinessAccountIndexDto);

  PaypalBusinessAccountIndexDto toDto(PaypalBusinessAccountEntity paypalBusinessAccountEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  PaypalBusinessAccountEntity partialUpdate(
      PaypalBusinessAccountIndexDto paypalBusinessAccountIndexDto,
      @MappingTarget PaypalBusinessAccountEntity paypalBusinessAccountEntity);
}
