package com.example.ecommerce_backend.mapper.SupplierEntity;

import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityUpdateDto;
import com.example.ecommerce_backend.model.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierEntityUpdateDtoMapper {
    SupplierEntity SupplierEntityUpdateDtoToSupplierEntity(SupplierEntityUpdateDto supplierEntityUpdateDto);
    SupplierEntityUpdateDto SupplierEntityToSupplierEntityUpdateDto(SupplierEntity supplierEntity);
}

