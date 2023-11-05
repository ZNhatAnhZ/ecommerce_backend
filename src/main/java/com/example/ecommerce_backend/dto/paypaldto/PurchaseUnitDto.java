package com.example.ecommerce_backend.dto.paypaldto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PurchaseUnitDto implements Serializable {

	@JsonProperty("reference_id")
	private String referenceId;

	@JsonProperty("invoice_id")
	private String invoiceId;

	private List<ItemDto> items;

	private AmountDto amount;

}
