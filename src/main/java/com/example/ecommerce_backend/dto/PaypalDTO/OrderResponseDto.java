package com.example.ecommerce_backend.dto.PaypalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto implements Serializable {
    private String id;
    private String status;
    private List<LinkDto> links;
}
