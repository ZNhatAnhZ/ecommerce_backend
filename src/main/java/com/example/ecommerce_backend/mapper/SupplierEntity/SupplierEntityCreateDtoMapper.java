package com.example.ecommerce_backend.mapper.SupplierEntity;

import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.model.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierEntityCreateDtoMapper {
    SupplierEntity SupplierEntityCreateDtoToSupplierEntity(SupplierEntityCreateDto supplierEntityCreateDto);
    SupplierEntityCreateDto SupplierEntityToSupplierEntityCreateDto(SupplierEntity supplierEntity);
}

