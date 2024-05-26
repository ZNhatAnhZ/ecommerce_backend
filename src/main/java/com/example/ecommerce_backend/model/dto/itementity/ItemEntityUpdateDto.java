package com.example.ecommerce_backend.model.dto.itementity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemEntityUpdateDto {

  private int id;

  private String price;

  private String stock;

  private boolean isDisabled;
}
