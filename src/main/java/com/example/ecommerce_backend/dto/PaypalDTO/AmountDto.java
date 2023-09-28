package com.example.ecommerce_backend.dto.PaypalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

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
