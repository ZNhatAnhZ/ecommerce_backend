package com.example.ecommerce_backend.model.dto.orderentity;

import com.example.ecommerce_backend.model.entity.OrderItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/** DTO for {@link OrderItemEntity} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemEntityCreateDto implements Serializable {

  private Integer cartItemId;

  private int quantity;

  private Integer itemEntityId;

  private String itemEntitySku;

  private Set<Integer> variationEntityIdSet;

  private Integer productId;
}
