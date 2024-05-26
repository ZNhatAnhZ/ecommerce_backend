package com.example.ecommerce_backend.model.dto.itementity;

import com.example.ecommerce_backend.model.dto.productentity.ProductEntityIndexDto;
import com.example.ecommerce_backend.model.dto.variationentity.VariationEntityForItemEntityDto;
import java.io.Serializable;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemEntityIndexDto implements Serializable {

  private int id;

  private String sku;

  private String price;

  private String stock;

  private boolean isDisabled;

  private ProductEntityIndexDto productEntity;

  private Set<VariationEntityForItemEntityDto> variationEntityList;
}
