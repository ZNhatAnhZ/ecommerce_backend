package com.example.ecommerce_backend.dto.cartitementity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** DTO for {@link com.example.ecommerce_backend.model.CartItemEntity} */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItemEntityCreateRequestDto implements Serializable {

  private Integer quantity;

  private int userId;

  private int itemEntityId;
}
