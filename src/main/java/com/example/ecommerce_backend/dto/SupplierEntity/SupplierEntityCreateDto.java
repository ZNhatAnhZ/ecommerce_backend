package com.example.ecommerce_backend.dto.SupplierEntity;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierEntityCreateDto {
    private String name;
    private String telephone;
    private String address1;
    private String address2;
}
