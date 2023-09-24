package com.example.ecommerce_backend.dto.PaypalDTO;

import com.example.ecommerce_backend.constant.PaypalOrderIntent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto implements Serializable {
    private PaypalOrderIntent intent;
    @JsonProperty("purchase_units")
    private List<PurchaseUnitDto> purchaseUnits;
    @JsonProperty("application_context")
    private ApplicationContextDto applicationContext;
}
