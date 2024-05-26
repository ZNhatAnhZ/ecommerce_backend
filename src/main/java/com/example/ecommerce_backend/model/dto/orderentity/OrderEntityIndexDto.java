package com.example.ecommerce_backend.model.dto.orderentity;

import com.example.ecommerce_backend.util.constant.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEntityIndexDto {

  private int id;

  private String paypalOrderId;

  private OrderStatus status;
}
