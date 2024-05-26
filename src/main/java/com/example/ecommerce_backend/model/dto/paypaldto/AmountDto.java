package com.example.ecommerce_backend.model.dto.paypaldto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class AmountDto implements Serializable {

  @JsonProperty("currency_code")
  private String currencyCode;

  private String value;

  private BreakdownDto breakdown;
}
