package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.supplierentity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.dto.supplierentity.SupplierEntityUpdateDto;
import com.example.ecommerce_backend.model.SupplierEntity;

import java.util.List;

public interface SupplierServiceInterface {

	List<SupplierEntity> getAllSuppliers();

	SupplierEntity getSupplierById(int id);

	SupplierEntity createSupplier(SupplierEntityCreateDto supplierEntityCreateDto);

	SupplierEntity updateSupplier(SupplierEntityUpdateDto supplierEntityUpdateDto);

	void deleteSupplier(int id);

}
