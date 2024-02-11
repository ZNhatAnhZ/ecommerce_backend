package com.example.ecommerce_backend.dto.paypaldto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ItemDto implements Serializable {

  private String name;

  private String quantity;

  private String description;

  private String sku;

  private String category;

  @JsonProperty("unit_amount")
  private AmountDto unitAmount;
}
