package com.example.ecommerce_backend.model.dto.paypaldto;

import com.example.ecommerce_backend.util.constant.OrderPaypalStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto implements Serializable {

  private String id;

  private OrderPaypalStatus status;

  private List<LinkDto> links;
}
