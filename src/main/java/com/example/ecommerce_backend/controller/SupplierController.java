package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityCreateDto;
import com.example.ecommerce_backend.dto.SupplierEntity.SupplierEntityUpdateDto;
import com.example.ecommerce_backend.model.SupplierEntity;
import com.example.ecommerce_backend.service.interfaces.SupplierServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/suppliers")
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierController {
    private final SupplierServiceInterface supplierServiceInterface;

    @GetMapping
    public List<SupplierEntity> getAllSuppliers() {
        return supplierServiceInterface.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierEntity> getSupplierById(@PathVariable int id) {
        Optional<SupplierEntity> supplier = supplierServiceInterface.getSupplierById(id);
        return supplier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SupplierEntity> createSupplier(@RequestBody SupplierEntityCreateDto supplierEntityCreateDto) {
        SupplierEntity createdSupplier = supplierServiceInterface.createSupplier(supplierEntityCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierEntity> updateSupplier(@PathVariable int id, @RequestBody SupplierEntityUpdateDto supplierEntityUpdateDto) {
        supplierEntityUpdateDto.setId(id);
        SupplierEntity updatedSupplier = supplierServiceInterface.updateSupplier(supplierEntityUpdateDto);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int id) {
        supplierServiceInterface.deleteSupplier(id);
        return ResponseEntity.ok().build();
    }
}
