package com.example.ecommerce_backend.dto.paypaldto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class DiscountDto {

	@JsonProperty("currency_code")
	private String currencyCode;

	private String value;

}
