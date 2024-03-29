package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.itementity.ItemEntityAfterCreatedDto;
import com.example.ecommerce_backend.dto.productentity.*;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.itementity.ItemEntityAfterCreatedDtoMapper;
import com.example.ecommerce_backend.mapper.productentity.ProductEntityAfterCreatedDtoMapper;
import com.example.ecommerce_backend.mapper.productentity.ProductEntityDetailDtoMapper;
import com.example.ecommerce_backend.mapper.productentity.ProductEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.productentity.ProductEntityUpdateDtoMapper;
import com.example.ecommerce_backend.mapper.variationentity.VariationEntityCreateDtoMapper;
import com.example.ecommerce_backend.model.*;
import com.example.ecommerce_backend.repository.ProductEntityRepository;
import com.example.ecommerce_backend.service.interfaces.*;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductServiceInterface {

  private final ProductEntityRepository productEntityRepository;

  private final ProductEntityIndexDtoMapper productEntityIndexDtoMapper;

  private final ProductEntityDetailDtoMapper productEntityDetailDtoMapper;

  private final ProductEntityUpdateDtoMapper productEntityUpdateDtoMapper;

  private final ProductEntityAfterCreatedDtoMapper productEntityAfterCreatedDtoMapper;

  private final ItemEntityAfterCreatedDtoMapper itemEntityAfterCreatedDtoMapper;

  private final VariationEntityCreateDtoMapper variationEntityCreateDtoMapper;

  @Qualifier("categoryServiceImpl")
  private final CategoryServiceInterface categoryServiceInterface;

  @Qualifier("discountServiceImpl")
  private final DiscountServiceInterface discountServiceInterface;

  @Qualifier("supplierServiceImpl")
  private final SupplierServiceInterface supplierServiceInterface;

  @Qualifier("variationServiceImpl")
  private final VariationServiceInterface variationServiceInterface;

  @Qualifier("itemServiceImpl")
  private final ItemServiceInterface itemServiceInterface;

  @Override
  public Page<ProductEntityIndexDto> getAllProducts(Pageable pageable) {
    Page<ProductEntity> productEntityPage = productEntityRepository.findAll(pageable);

    return productEntityPage.map(productEntityIndexDtoMapper::toDto);
  }

  @Override
  public ProductEntityDetailDto getProductById(Integer id) {
    Optional<ProductEntity> productEntity = productEntityRepository.findById(id);

    if (productEntity.isPresent()) {
      productEntity
          .get()
          .setVariationEntitySet(
              productEntity.get().getVariationEntitySet().stream()
                  .filter(variationEntity -> variationEntity.getParentVariationEntity() == null)
                  .collect(Collectors.toSet()));
      return productEntityDetailDtoMapper.toDto(productEntity.get());
    } else {
      throw new ResourceNotFoundException("Could not find product with id " + id);
    }
  }

  @Override
  public Page<ProductEntityIndexDto> searchProductByName(String name, Pageable pageable) {
    Page<ProductEntity> productEntityPage =
        productEntityRepository.findAllByNameContainsIgnoreCase(name, pageable);

    return productEntityPage.map(productEntityIndexDtoMapper::toDto);
  }

  @Override
  public ProductEntityAfterCreatedDto createProduct(ProductEntityCreateDto productEntityCreateDto) {
    ProductCategoryEntity productCategoryEntity =
        categoryServiceInterface.getCategoryById(productEntityCreateDto.getCategoryId());
    SupplierEntity supplierEntity =
        supplierServiceInterface.getSupplierById(productEntityCreateDto.getSupplierId());
    DiscountEntity discountEntity =
        discountServiceInterface.getDiscountById(productEntityCreateDto.getDiscountId());
    Set<VariationEntity> variationEntitySet =
        new HashSet<>(
            variationEntityCreateDtoMapper.toEntityList(
                productEntityCreateDto.getVariationEntityCreateDtoList()));

    ProductEntity productEntity =
        ProductEntity.builder()
            .name(productEntityCreateDto.getName())
            .description(productEntityCreateDto.getDescription())
            .categoryEntity(productCategoryEntity)
            .discountEntity(discountEntity)
            .supplierEntity(supplierEntity)
            .build();

    variationEntitySet.forEach(
        variationEntity ->
            updateParentVariationAndProductEntityForAllChildVariations(
                productEntity, variationEntity, variationEntity.getChildVariationEntityList()));

    productEntity.setVariationEntitySet(variationEntitySet);
    ProductEntity savedProductEntity = productEntityRepository.save(productEntity);

    List<ItemEntity> itemEntityList =
        itemServiceInterface.createAllItemsBasedOnProductEntity(savedProductEntity);
    List<ItemEntityAfterCreatedDto> itemEntityAfterCreatedDtoList =
        itemEntityList.stream().map(itemEntityAfterCreatedDtoMapper::toDto).toList();
    ProductEntityAfterCreatedDto productEntityAfterCreatedDto =
        productEntityAfterCreatedDtoMapper.toDto(savedProductEntity);
    productEntityAfterCreatedDto.setItemEntityAfterCreatedDtoList(itemEntityAfterCreatedDtoList);

    return productEntityAfterCreatedDto;
  }

  @Override
  public ProductEntityIndexDto updateProduct(ProductEntityUpdateDto productEntityUpdateDto) {
    Optional<ProductEntity> productEntity =
        productEntityRepository.findById(productEntityUpdateDto.getId());
    if (productEntity.isPresent()) {
      ProductCategoryEntity productCategoryEntity =
          categoryServiceInterface.getCategoryById(productEntityUpdateDto.getCategoryId());
      SupplierEntity supplierEntity =
          supplierServiceInterface.getSupplierById(productEntityUpdateDto.getSupplierId());
      DiscountEntity discountEntity =
          discountServiceInterface.getDiscountById(productEntityUpdateDto.getDiscountId());
      Set<VariationEntity> variationEntitySet =
          new HashSet<>(
              variationEntityCreateDtoMapper.toEntityList(
                  productEntityUpdateDto.getVariationEntityCreateDtoList()));

      variationEntitySet.forEach(
          variationEntity ->
              updateParentVariationAndProductEntityForAllChildVariations(
                  productEntity.get(),
                  variationEntity,
                  variationEntity.getChildVariationEntityList()));
      variationServiceInterface.deleteVariationInBatch(productEntity.get().getVariationEntitySet());

      ProductEntity updatedProductEntity =
          productEntityUpdateDtoMapper.toEntity(productEntityUpdateDto);
      updatedProductEntity.setCategoryEntity(productCategoryEntity);
      updatedProductEntity.setSupplierEntity(supplierEntity);
      updatedProductEntity.setDiscountEntity(discountEntity);
      updatedProductEntity.setVariationEntitySet(variationEntitySet);

      return productEntityIndexDtoMapper.toDto(productEntityRepository.save(updatedProductEntity));
    } else {
      throw new ResourceNotFoundException(
          "Could not find product with id " + productEntityUpdateDto.getId());
    }
  }

  @Override
  public void deleteProduct(int id) {
    if (productEntityRepository.existsById(id)) {
      productEntityRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("Could not find product with id " + id);
    }
  }

  private void updateParentVariationAndProductEntityForAllChildVariations(
      ProductEntity productEntity, VariationEntity parent, List<VariationEntity> children) {
    if (productEntity != null && parent != null) {
      parent.setProductEntity(productEntity);

      if (children != null) {
        children.forEach(
            child -> {
              child.setProductEntity(productEntity);
              child.setParentVariationEntity(parent);
              updateParentVariationAndProductEntityForAllChildVariations(
                  productEntity, child, child.getChildVariationEntityList());
            });
      }
    }
  }
}
