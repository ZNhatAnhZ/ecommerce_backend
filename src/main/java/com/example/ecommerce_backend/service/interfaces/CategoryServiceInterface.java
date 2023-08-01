package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityUpdateDto;
import com.example.ecommerce_backend.model.ProductCategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {
    List<ProductCategoryEntity> getAllCategories();
    Optional<ProductCategoryEntity> getCategoryById(int id);
    ProductCategoryEntity createCategory(CategoryEntityCreateDto categoryEntityCreateDto);
    ProductCategoryEntity updateCategory(CategoryEntityUpdateDto categoryEntityUpdateDto);
    void deleteCategory(int id);
}
