package com.example.ecommerce_backend.model.mapper.supplierentity;

import com.example.ecommerce_backend.model.dto.supplierentity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.model.entity.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierEntityCreateDtoMapper {

  SupplierEntity toEntity(SupplierEntityCreateDto supplierEntityCreateDto);

  SupplierEntityCreateDto toDto(SupplierEntity supplierEntity);
}
