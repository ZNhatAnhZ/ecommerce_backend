package com.example.ecommerce_backend.dto.PaypalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseUnitDto implements Serializable {
    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("invoice_id")
    private String invoiceId;
    private List<ItemDto> items;
    private AmountDto amount;
}
