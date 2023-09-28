package com.example.ecommerce_backend.dto.PaypalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BreakdownDto implements Serializable {
    @JsonProperty("item_total")
    ItemTotalDto itemTotal;
    DiscountDto discount;
}
