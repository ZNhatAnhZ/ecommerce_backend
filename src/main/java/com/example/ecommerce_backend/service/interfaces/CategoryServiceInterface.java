package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.productcategoryentity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.dto.productcategoryentity.CategoryEntityUpdateDto;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import java.util.List;

public interface CategoryServiceInterface {

  List<ProductCategoryEntity> getAllCategories();

  ProductCategoryEntity getCategoryById(int id);

  ProductCategoryEntity createCategory(CategoryEntityCreateDto categoryEntityCreateDto);

  ProductCategoryEntity updateCategory(CategoryEntityUpdateDto categoryEntityUpdateDto);

  void deleteCategory(int id);
}
