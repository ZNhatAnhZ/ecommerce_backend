package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.ItemEntity;
import com.example.ecommerce_backend.model.ProductEntity;

import java.util.List;

public interface ItemServiceInterface {
    List<ItemEntity> createAllItemsBasedOnProductEntity(ProductEntity productEntity);
}
