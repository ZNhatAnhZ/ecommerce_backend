package com.example.ecommerce_backend.model.dto.paypaldto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BreakdownDto implements Serializable {

  @JsonProperty("item_total")
  ItemTotalDto itemTotal;

  DiscountDto discount;
}
