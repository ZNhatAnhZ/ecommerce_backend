package com.example.ecommerce_backend.model.dto.orderentity;

import com.example.ecommerce_backend.util.constant.OrderCreateType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEntityCreateDto implements Serializable {

  private OrderCreateType orderCreateType;

  private int userId;

  private String email;

  private List<OrderItemEntityCreateDto> itemList;
}
