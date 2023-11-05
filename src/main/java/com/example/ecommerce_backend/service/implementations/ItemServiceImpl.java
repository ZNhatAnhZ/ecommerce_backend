package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.itementity.ItemEntityIndexDto;
import com.example.ecommerce_backend.dto.itementity.ItemEntityUpdateDto;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.itementity.ItemEntityIndexDtoMapper;
import com.example.ecommerce_backend.mapper.itementity.ItemEntityUpdateDtoMapper;
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

	private final ItemEntityIndexDtoMapper itemEntityIndexDtoMapper;

	private final ItemEntityUpdateDtoMapper itemEntityUpdateDtoMapper;

	@Override
	public List<ItemEntity> createAllItemsBasedOnProductEntity(ProductEntity productEntity) {
		List<ItemEntity> itemEntityList = new ArrayList<>();
		productEntity.getVariationEntitySet()
			.stream()
			.filter(variationEntity -> variationEntity.getParentVariationEntity() == null)
			.forEach(variationEntity -> {
				List<Set<VariationEntity>> allCombinationForCurrentVariationEntity = getAllCombinationsOfCurrentVariationEntity(
						variationEntity);
				allCombinationForCurrentVariationEntity.forEach(combinationOfVariationEntity -> {
					ItemEntity newItemEntity = ItemEntity.builder()
						.isDisabled(true)
						.sku(UUID.randomUUID().toString())
						.productEntity(productEntity)
						.variationEntityList(combinationOfVariationEntity)
						.build();

					itemEntityList.add(itemEntityRepository.save(newItemEntity));
				});
			});

		return itemEntityList;
	}

	@Override
	public ItemEntityIndexDto updateItemEntity(ItemEntityUpdateDto itemEntityUpdateDto) {
		Optional<ItemEntity> itemEntity = itemEntityRepository.findById(itemEntityUpdateDto.getId());
		if (itemEntity.isPresent()) {
			itemEntity.get().setStock(itemEntityUpdateDto.getStock());
			itemEntity.get().setPrice(itemEntityUpdateDto.getPrice());
			itemEntity.get().setDisabled(itemEntityUpdateDto.isDisabled());
			return itemEntityIndexDtoMapper.toDto(itemEntityRepository.save(itemEntity.get()));
		}
		else {
			throw new ResourceNotFoundException("Could not find item entity with id " + itemEntityUpdateDto.getId());
		}
	}

	@Override
	public List<ItemEntityIndexDto> batchUpdateItemEntity(List<ItemEntityUpdateDto> itemEntityUpdateDtoList) {
		List<ItemEntity> itemEntityList = itemEntityUpdateDtoList.stream().map(itemEntityUpdateDto -> {
			Optional<ItemEntity> itemEntity = itemEntityRepository.findById(itemEntityUpdateDto.getId());

			if (itemEntity.isPresent()) {
				itemEntity.get().setStock(itemEntityUpdateDto.getStock());
				itemEntity.get().setPrice(itemEntityUpdateDto.getPrice());
				itemEntity.get().setDisabled(itemEntityUpdateDto.isDisabled());

				return itemEntity.get();
			}
			else {
				throw new ResourceNotFoundException(
						"Could not find item entity with id " + itemEntityUpdateDto.getId());
			}
		}).toList();

		return itemEntityRepository.saveAll(itemEntityList)
			.stream()
			.map(itemEntityIndexDtoMapper::toDto)
			.toList();
	}

	public ItemEntity findItemEntityById(int id) {
		Optional<ItemEntity> itemEntity = itemEntityRepository.findById(id);
		if (itemEntity.isPresent()) {
			return itemEntity.get();
		}
		else {
			throw new ResourceNotFoundException("Could not find item entity with id " + id);
		}
	}

	private List<Set<VariationEntity>> getAllCombinationsOfCurrentVariationEntity(VariationEntity variationEntity) {
		List<Set<VariationEntity>> listOfCombination = new ArrayList<>();

		if (variationEntity.getChildVariationEntityList() != null) {
			variationEntity.getChildVariationEntityList().forEach(childVariationEntity -> {
				List<Set<VariationEntity>> variationEntitiesFromChild = getAllCombinationsOfCurrentVariationEntity(
						childVariationEntity);
				listOfCombination.addAll(variationEntitiesFromChild);
			});
		}
		else {
			Set<VariationEntity> defaultSet = new HashSet<>();
			listOfCombination.add(defaultSet);
		}

		listOfCombination.forEach(setOfVariationEntity -> setOfVariationEntity.add(variationEntity));

		return listOfCombination;
	}

}
