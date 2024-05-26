package com.example.ecommerce_backend.model.mapper.paypalbusinessaccount;

import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountCreateDto;
import com.example.ecommerce_backend.model.entity.PaypalBusinessAccountEntity;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaypalBusinessAccountCreateMapper {
  PaypalBusinessAccountEntity toEntity(
      PaypalBusinessAccountCreateDto paypalBusinessAccountCreateDto);

  PaypalBusinessAccountCreateDto toDto(PaypalBusinessAccountEntity paypalBusinessAccountEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  PaypalBusinessAccountEntity partialUpdate(
      PaypalBusinessAccountCreateDto paypalBusinessAccountCreateDto,
      @MappingTarget PaypalBusinessAccountEntity paypalBusinessAccountEntity);
}
