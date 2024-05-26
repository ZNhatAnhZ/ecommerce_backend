package com.example.ecommerce_backend.model.dto.cartitementity;

import com.example.ecommerce_backend.model.dto.itementity.ItemEntityIndexDto;
import com.example.ecommerce_backend.model.entity.CartItemEntity;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/** DTO for {@link CartItemEntity} */
@Data
@Builder
public class CartItemEntityIndexDto implements Serializable {

  private final int id;

  private final Integer quantity;

  private final String sku;

  private final ItemEntityIndexDto itemEntity;
}
