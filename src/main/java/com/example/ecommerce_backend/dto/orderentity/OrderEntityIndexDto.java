package com.example.ecommerce_backend.dto.orderentity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEntityIndexDto {

  private int id;

  private String paypalOrderId;

  private String status;
}
