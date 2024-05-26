package com.example.ecommerce_backend.model.mapper.supplierentity;

import com.example.ecommerce_backend.model.dto.supplierentity.SupplierEntityUpdateDto;
import com.example.ecommerce_backend.model.entity.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierEntityUpdateDtoMapper {

  SupplierEntity toEntity(SupplierEntityUpdateDto supplierEntityUpdateDto);

  SupplierEntityUpdateDto toDto(SupplierEntity supplierEntity);
}
