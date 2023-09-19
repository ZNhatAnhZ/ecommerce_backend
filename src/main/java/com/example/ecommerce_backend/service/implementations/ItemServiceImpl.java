package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.model.ItemEntity;
import com.example.ecommerce_backend.model.ProductEntity;
import com.example.ecommerce_backend.model.VariationEntity;
import com.example.ecommerce_backend.repository.ItemEntityRepository;
import com.example.ecommerce_backend.service.interfaces.ItemServiceInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ItemServiceImpl implements ItemServiceInterface {
    private final ItemEntityRepository itemEntityRepository;
    @Override
    public void createAllItemsBasedOnProductEntity(ProductEntity productEntity) {
        productEntity.getVariationEntitySet()
                .stream()
                .filter(variationEntity -> variationEntity.getParentVariationEntity() == null)
                .forEach(variationEntity -> {
                    List<Set<VariationEntity>> allCombinationForCurrentVariationEntity = getAllCombinationsOfCurrentVariationEntity(variationEntity);
                    allCombinationForCurrentVariationEntity.forEach((combinationOfVariationEntity) -> {
                        ItemEntity newItemEntity = ItemEntity.builder()
                                .isDisabled(true)
                                .sku(UUID.randomUUID().toString())
                                .productEntity(productEntity)
                                .variationEntityList(combinationOfVariationEntity)
                                .build();

                        itemEntityRepository.save(newItemEntity);
                    });
                });
    }

    private List<Set<VariationEntity>> getAllCombinationsOfCurrentVariationEntity(VariationEntity variationEntity) {
        List<Set<VariationEntity>> listOfCombination = new ArrayList<>();

        if (variationEntity.getChildVariationEntityList() != null) {
            variationEntity.getChildVariationEntityList().forEach((childVariationEntity) -> {
                List<Set<VariationEntity>> variationEntitiesFromChild = getAllCombinationsOfCurrentVariationEntity(childVariationEntity);
                listOfCombination.addAll(variationEntitiesFromChild);
            });
        } else {
            Set<VariationEntity> defaultSet = new HashSet<>();
            listOfCombination.add(defaultSet);
        }

        listOfCombination.forEach((setOfVariationEntity) -> {
            setOfVariationEntity.add(variationEntity);
        });

        return listOfCombination;
    }
}
