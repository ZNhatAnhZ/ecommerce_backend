package com.example.ecommerce_backend.dto.orderentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/** DTO for {@link com.example.ecommerce_backend.model.OrderItemEntity} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemEntityCreateDto implements Serializable {

  private Integer cartItemId;

  private int quantity;

  private int itemEntityId;

  private String itemEntitySku;
}
