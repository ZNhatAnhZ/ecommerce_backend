package com.example.ecommerce_backend.mapper.supplierentity;

import com.example.ecommerce_backend.dto.supplierentity.SupplierEntityUpdateDto;
import com.example.ecommerce_backend.model.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierEntityUpdateDtoMapper {

	SupplierEntity toEntity(SupplierEntityUpdateDto supplierEntityUpdateDto);

	SupplierEntityUpdateDto toDto(SupplierEntity supplierEntity);

}
