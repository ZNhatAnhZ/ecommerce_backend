package com.example.ecommerce_backend.model.mapper.paypalbusinessaccount;

import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountUpdateDto;
import com.example.ecommerce_backend.model.entity.PaypalBusinessAccountEntity;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaypalBusinessAccountUpdateMapper {
  PaypalBusinessAccountEntity toEntity(
      PaypalBusinessAccountUpdateDto paypalBusinessAccountUpdateDto);

  PaypalBusinessAccountUpdateDto toDto(PaypalBusinessAccountEntity paypalBusinessAccountEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  PaypalBusinessAccountEntity partialUpdate(
      PaypalBusinessAccountUpdateDto paypalBusinessAccountUpdateDto,
      @MappingTarget PaypalBusinessAccountEntity paypalBusinessAccountEntity);
}
