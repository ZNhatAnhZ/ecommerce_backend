package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.ProductEntity;

public interface ItemServiceInterface {
    void createAllItemsBasedOnProductEntity(ProductEntity productEntity);
}
