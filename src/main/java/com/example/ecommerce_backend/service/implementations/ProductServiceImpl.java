package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityCreateDto;
import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityDetailDto;
import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityIndexDto;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.ProductEntity.ProductEntityDetailDtoMapper;
import com.example.ecommerce_backend.mapper.ProductEntity.ProductEntityIndexDtoMapper;
import com.example.ecommerce_backend.model.DiscountEntity;
import com.example.ecommerce_backend.model.ProductCategoryEntity;
import com.example.ecommerce_backend.model.ProductEntity;
import com.example.ecommerce_backend.model.SupplierEntity;
import com.example.ecommerce_backend.repository.ProductEntityRepository;
import com.example.ecommerce_backend.service.interfaces.CategoryServiceInterface;
import com.example.ecommerce_backend.service.interfaces.DiscountServiceInterface;
import com.example.ecommerce_backend.service.interfaces.ProductServiceInterface;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class ProductServiceImpl implements ProductServiceInterface {
    private final ProductEntityRepository productEntityRepository;
    private final ProductEntityIndexDtoMapper productEntityIndexDtoMapper;
    private final ProductEntityDetailDtoMapper productEntityDetailDtoMapper;
    private final CategoryServiceInterface categoryServiceInterface;
    private final DiscountServiceInterface discountServiceInterface;
    private final SupplierServiceInterface supplierServiceInterface;

    public List<ProductEntityIndexDto> getAllProducts() {
        List<ProductEntity> productEntityList = productEntityRepository.findAll();

        return productEntityList.stream().map(productEntityIndexDtoMapper::ProductEntityToProductEntityIndexDto).toList();
    }

    public ProductEntityDetailDto getProductById(Integer id) {
        Optional<ProductEntity> productEntity = productEntityRepository.findById(id);

        if (productEntity.isPresent()) {
            return productEntityDetailDtoMapper.ProductEntityToProductEntityDetailDto(productEntity.get());
        } else {
            throw new ResourceNotFoundException("Could not find product with id " + id);
        }
    }

    public ProductEntity createProduct(ProductEntityCreateDto productEntityCreateDto) {
        ProductCategoryEntity productCategoryEntity = categoryServiceInterface.getCategoryById(productEntityCreateDto.getCategoryId());
        SupplierEntity supplierEntity = supplierServiceInterface.getSupplierById(productEntityCreateDto.getSupplierId());
        DiscountEntity discountEntity = discountServiceInterface.getDiscountById(productEntityCreateDto.getDiscountId());

        ProductEntity productEntity = ProductEntity.builder()
                .name(productEntityCreateDto.getName())
                .description(productEntityCreateDto.getDescription())
                .categoryEntity(productCategoryEntity)
                .discountEntity(discountEntity)
                .supplierEntity(supplierEntity)
                .build();
        return productEntityRepository.save(productEntity);
    }

//    public Product updateProduct(Product product) {
//        return productRepository.save(product);
//    }

    public void deleteProduct(int id) {
        if (productEntityRepository.existsById(id)) {
            productEntityRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Could not find user with id " + id);
        }
    }
}
