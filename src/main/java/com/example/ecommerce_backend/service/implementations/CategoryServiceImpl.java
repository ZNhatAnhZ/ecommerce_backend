package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityCreateDto;
import com.example.ecommerce_backend.dto.ProductCategoryEntity.CategoryEntityUpdateDto;
import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.CategoryEntity.CategoryEntityCreateDtoMapper;
import com.example.ecommerce_backend.mapper.CategoryEntity.CategoryEntityUpdateDtoMapper;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import com.example.ecommerce_backend.repository.ProductCategoryEntityRepository;
import com.example.ecommerce_backend.service.interfaces.CategoryServiceInterface;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class CategoryServiceImpl implements CategoryServiceInterface {
    private final ProductCategoryEntityRepository productCategoryEntityRepository;
    private final CategoryEntityCreateDtoMapper categoryEntityCreateDtoMapper;
    private final CategoryEntityUpdateDtoMapper categoryEntityUpdateDtoMapper;

    public List<ProductCategoryEntity> getAllCategories() {
        return productCategoryEntityRepository.findAll();
    }

    public ProductCategoryEntity getCategoryById(int id) {
        Optional<ProductCategoryEntity> productCategoryEntity = productCategoryEntityRepository.findById(id);
        if (productCategoryEntity.isPresent()) {
            return productCategoryEntity.get();
        } else {
            throw new ResourceNotFoundException("Could not find category with id " + id);
        }
    }

    public ProductCategoryEntity createCategory(CategoryEntityCreateDto categoryEntityCreateDto) {
        if (productCategoryEntityRepository.existsByName(categoryEntityCreateDto.getName())) {
            throw new ResourceDuplicateException("this category is already exist");
        } else {
            ProductCategoryEntity productCategoryEntity = categoryEntityCreateDtoMapper.CategoryEntityCreateDtoToProductCategoryEntity(categoryEntityCreateDto);
            return productCategoryEntityRepository.save(productCategoryEntity);
        }
    }

    public ProductCategoryEntity updateCategory(CategoryEntityUpdateDto categoryEntityUpdateDto) {
        if (productCategoryEntityRepository.existsById(categoryEntityUpdateDto.getId())) {
            ProductCategoryEntity productCategoryEntity = categoryEntityUpdateDtoMapper.CategoryEntityUpdateDtoToProductCategoryEntity(categoryEntityUpdateDto);
            return productCategoryEntityRepository.save(productCategoryEntity);
        } else {
            throw new ResourceNotFoundException("Could not find category with id " + categoryEntityUpdateDto.getId());
        }
    }

    public void deleteCategory(int id) {
        if (productCategoryEntityRepository.existsById(id)) {
            productCategoryEntityRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Could not find category with id " + id);
        }
    }
}
