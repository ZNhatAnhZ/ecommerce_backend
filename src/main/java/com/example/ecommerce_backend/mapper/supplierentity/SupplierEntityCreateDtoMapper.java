package com.example.ecommerce_backend.mapper.supplierentity;

import com.example.ecommerce_backend.dto.supplierentity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.model.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierEntityCreateDtoMapper {

  SupplierEntity toEntity(SupplierEntityCreateDto supplierEntityCreateDto);

  SupplierEntityCreateDto toDto(SupplierEntity supplierEntity);
}
