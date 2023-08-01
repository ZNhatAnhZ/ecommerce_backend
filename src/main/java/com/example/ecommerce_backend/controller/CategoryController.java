package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityUpdateDto;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import com.example.ecommerce_backend.service.interfaces.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/categories")
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
    private final CategoryServiceInterface categoryServiceInterface;
    @GetMapping
    public List<ProductCategoryEntity> getAllCategories() {
        return categoryServiceInterface.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryEntity> getCategoryById(@PathVariable int id) {
        Optional<ProductCategoryEntity> category = categoryServiceInterface.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductCategoryEntity> createCategory(@RequestBody CategoryEntityCreateDto categoryEntityCreateDto) {
        ProductCategoryEntity createdCategory = categoryServiceInterface.createCategory(categoryEntityCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryEntity> updateCategory(@PathVariable int id, @RequestBody CategoryEntityUpdateDto categoryEntityUpdateDto) {
        categoryEntityUpdateDto.setId(id);
        ProductCategoryEntity productCategoryEntity = categoryServiceInterface.updateCategory(categoryEntityUpdateDto);

        return ResponseEntity.ok(productCategoryEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryServiceInterface.deleteCategory(id);

        return ResponseEntity.ok().build();
    }
}
