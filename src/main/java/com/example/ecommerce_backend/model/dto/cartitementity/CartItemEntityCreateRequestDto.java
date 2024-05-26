package com.example.ecommerce_backend.model.dto.cartitementity;

import com.example.ecommerce_backend.model.entity.CartItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** DTO for {@link CartItemEntity} */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItemEntityCreateRequestDto implements Serializable {

  private Integer quantity;

  private Integer userId;

  private Integer itemEntityId;

  private Set<Integer> variationEntityIdSet;

  private Integer productId;
}
