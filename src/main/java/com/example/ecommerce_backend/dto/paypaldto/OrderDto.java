package com.example.ecommerce_backend.dto.paypaldto;

import com.example.ecommerce_backend.constant.PaypalOrderIntent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class OrderDto implements Serializable {

  private PaypalOrderIntent intent;

  @JsonProperty("purchase_units")
  private List<PurchaseUnitDto> purchaseUnits;

  @JsonProperty("application_context")
  private ApplicationContextDto applicationContext;
}
