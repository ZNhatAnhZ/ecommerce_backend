package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityCreateDto;
import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityDetailDto;
import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityIndexDto;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.ProductEntity.ProductEntityDetailDtoMapper;
import com.example.ecommerce_backend.mapper.ProductEntity.ProductEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.VariationEntity.VariationEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.*;
import com.example.ecommerce_backend.repository.ProductEntityRepository;
import com.example.ecommerce_backend.service.interfaces.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.ecommerce_backend.model.VariationEntity.updateParentVariationAndProductEntityForAllChildVariations;

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
    private final VariationServiceInterface variationServiceInterface;
    private final VariationEntityCreateDtoMapper variationEntityCreateDtoMapper;

    public Page<ProductEntityIndexDto> getAllProducts(Pageable pageable) {
        Page<ProductEntity> productEntityPage = productEntityRepository.findAll(pageable);

        return productEntityPage.map(productEntityIndexDtoMapper::ProductEntityToProductEntityIndexDto);
    }

    public ProductEntityDetailDto getProductById(Integer id) {
        Optional<ProductEntity> productEntity = productEntityRepository.findById(id);

        if (productEntity.isPresent()) {
            productEntity.get()
                    .setVariationEntitySet(productEntity.get()
                            .getVariationEntitySet()
                            .stream()
                            .filter(variationEntity -> variationEntity.getParentVariationEntity() == null)
                            .collect(Collectors.toSet())
            );
            return productEntityDetailDtoMapper.ProductEntityToProductEntityDetailDto(productEntity.get());
        } else {
            throw new ResourceNotFoundException("Could not find product with id " + id);
        }
    }

    public ProductEntityIndexDto createProduct(ProductEntityCreateDto productEntityCreateDto) {
        ProductCategoryEntity productCategoryEntity = categoryServiceInterface.getCategoryById(productEntityCreateDto.getCategoryId());
        SupplierEntity supplierEntity = supplierServiceInterface.getSupplierById(productEntityCreateDto.getSupplierId());
        DiscountEntity discountEntity = discountServiceInterface.getDiscountById(productEntityCreateDto.getDiscountId());
        Set<VariationEntity> variationEntitySet = new HashSet<>(variationEntityCreateDtoMapper.VariationEntityCreateDtoListToVariationEntityList(productEntityCreateDto.getVariationEntityCreateDtoList()));

        ProductEntity productEntity = ProductEntity.builder()
                .name(productEntityCreateDto.getName())
                .description(productEntityCreateDto.getDescription())
                .categoryEntity(productCategoryEntity)
                .discountEntity(discountEntity)
                .supplierEntity(supplierEntity)
                .build();

        variationEntitySet.forEach(variationEntity -> {
            updateParentVariationAndProductEntityForAllChildVariations(productEntity, variationEntity, variationEntity.getChildVariationEntityList());
        });

        productEntity.setVariationEntitySet(variationEntitySet);

        return productEntityIndexDtoMapper.ProductEntityToProductEntityIndexDto(productEntityRepository.save(productEntity));
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

    private void updateParentVariationAndProductEntityForAllChildVariations(ProductEntity productEntity, VariationEntity parent, List<VariationEntity> children) {
        if (productEntity != null && parent != null) {
            parent.setProductEntity(productEntity);

            if (children != null) {
                children.forEach((child) -> {
                    child.setProductEntity(productEntity);
                    child.setParentVariationEntity(parent);
                    updateParentVariationAndProductEntityForAllChildVariations(productEntity, child, child.getChildVariationEntityList());
                });
            }
        }
    }
}
