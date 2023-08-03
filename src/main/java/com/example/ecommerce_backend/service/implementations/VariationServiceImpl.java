package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityCreateDto;
import com.example.ecommerce_backend.dto.VariationEntity.VariationEntityIndexDto;
import com.example.ecommerce_backend.model.SupplierEntity;
import com.example.ecommerce_backend.model.VariationEntity;
import com.example.ecommerce_backend.service.interfaces.VariationServiceInterface;

public class VariationServiceImpl implements VariationServiceInterface {
    public VariationEntityIndexDto createVariation(VariationEntityCreateDto variationEntityCreateDto) {
        SupplierEntity supplierEntity = supplierEntityCreateDtoMapper.SupplierEntityCreateDtoToSupplierEntity(supplierEntityCreateDto);
        return supplierEntityRepository.save(supplierEntity);
    }
}
