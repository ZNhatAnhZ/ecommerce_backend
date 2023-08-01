package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityUpdateDto;
import com.example.ecommerce_backend.model.SupplierEntity;

import java.util.List;
import java.util.Optional;

public interface SupplierServiceInterface {
    List<SupplierEntity> getAllSuppliers();
    Optional<SupplierEntity> getSupplierById(int id);
    SupplierEntity createSupplier(SupplierEntityCreateDto supplierEntityCreateDto);
    SupplierEntity updateSupplier(SupplierEntityUpdateDto supplierEntityUpdateDto);
    void deleteSupplier(int id);
}
