package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.itementity.ItemEntityIndexDto;
import com.example.ecommerce_backend.dto.itementity.ItemEntityUpdateDto;
import com.example.ecommerce_backend.model.ItemEntity;
import com.example.ecommerce_backend.model.ProductEntity;
import java.util.List;
import java.util.Set;

public interface ItemServiceInterface {

  ItemEntity findItemEntityUsingVariationEntityIdSet(
      int productId, Set<Integer> variationEntityIdSet);

  List<ItemEntity> createAllItemsBasedOnProductEntity(ProductEntity productEntity);

  ItemEntityIndexDto updateItemEntity(ItemEntityUpdateDto itemEntityUpdateDto);

  List<ItemEntityIndexDto> batchUpdateItemEntity(List<ItemEntityUpdateDto> itemEntityUpdateDtoList);

  ItemEntity findItemEntityById(int id);
}
