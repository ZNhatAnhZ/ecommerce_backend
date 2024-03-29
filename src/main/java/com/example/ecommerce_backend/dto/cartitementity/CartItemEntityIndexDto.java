package com.example.ecommerce_backend.dto.cartitementity;

import com.example.ecommerce_backend.dto.itementity.ItemEntityIndexDto;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/** DTO for {@link com.example.ecommerce_backend.model.CartItemEntity} */
@Data
@Builder
public class CartItemEntityIndexDto implements Serializable {

  private final int id;

  private final Integer quantity;

  private final String sku;

  private final ItemEntityIndexDto itemEntity;
}
