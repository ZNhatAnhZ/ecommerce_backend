package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.entity.ItemEntity;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Integer> {
  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM item left join item_variation on item.id = item_variation.item_id where item.product_id = ?1 and (select count(*) from ecommerce.item_variation where variation_id in (?2) and ecommerce.item_variation.item_id = id) = ?3 limit 1")
  Optional<ItemEntity> findItemEntityByVariationEntityIds(
      int productId, Set<Integer> variationEntityIds, int variationEntityIdsSetSize);
}
