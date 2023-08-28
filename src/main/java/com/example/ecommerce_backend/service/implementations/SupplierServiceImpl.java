package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityUpdateDto;
import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.SupplierEntity.SupplierEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.SupplierEntity.SupplierEntityUpdateDtoMapper;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import com.example.ecommerce_backend.model.SupplierEntity;
import com.example.ecommerce_backend.repository.SupplierEntityRepository;
import com.example.ecommerce_backend.service.interfaces.SupplierServiceInterface;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Transactional
public class SupplierServiceImpl implements SupplierServiceInterface {
    private final SupplierEntityRepository supplierEntityRepository;
    private final SupplierEntityCreateDtoMapper supplierEntityCreateDtoMapper;
    private final SupplierEntityUpdateDtoMapper supplierEntityUpdateDtoMapper;

    public List<SupplierEntity> getAllSuppliers() {
        return supplierEntityRepository.findAll();
    }

    public SupplierEntity getSupplierById(int id) {
        Optional<SupplierEntity> supplierEntity = supplierEntityRepository.findById(id);
        if (supplierEntity.isPresent()) {
            return supplierEntity.get();
        } else {
            throw new ResourceNotFoundException("Could not find supplier with id " + id);
        }
    }

    public SupplierEntity createSupplier(SupplierEntityCreateDto supplierEntityCreateDto) {
        if (supplierEntityRepository.existsByName(supplierEntityCreateDto.getName())) {
            throw new ResourceDuplicateException("Supplier with this name already exists");
        } else {
            SupplierEntity supplierEntity = supplierEntityCreateDtoMapper.SupplierEntityCreateDtoToSupplierEntity(supplierEntityCreateDto);
            return supplierEntityRepository.save(supplierEntity);
        }
    }

    public SupplierEntity updateSupplier(SupplierEntityUpdateDto supplierEntityUpdateDto) {
        if (supplierEntityRepository.existsById(supplierEntityUpdateDto.getId())) {
            SupplierEntity supplierEntity = supplierEntityUpdateDtoMapper.SupplierEntityUpdateDtoToSupplierEntity(supplierEntityUpdateDto);
            return supplierEntityRepository.save(supplierEntity);
        } else {
            throw new ResourceNotFoundException("Could not find supplier with id " + supplierEntityUpdateDto.getId());
        }
    }

    public void deleteSupplier(int id) {
        if (supplierEntityRepository.existsById(id)) {
            supplierEntityRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Could not find supplier with id " + id);
        }
    }
}
