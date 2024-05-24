package com.example.ecommerce_backend.dto.orderentity;

import com.example.ecommerce_backend.constant.OrderStatus;
import com.example.ecommerce_backend.dto.discountentity.DiscountEntityIndexDto;
import com.example.ecommerce_backend.dto.itementity.ItemEntityIndexDto;
import com.example.ecommerce_backend.model.OrderEntity;
import com.example.ecommerce_backend.model.OrderItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link OrderEntity} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEntityIndexExtendedDto {
  private int id;
  private String subTotal;
  private String grandTotal;
  private OrderStatus status;
  private String shipping;
  private String paypalOrderId;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private String email;
  private DiscountEntityIndexDto discountEntity;
  private List<OrderItemEntityDto> orderItemEntityList;

  /** DTO for {@link OrderItemEntity} */
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class OrderItemEntityDto implements Serializable {
    private int id;
    private int quantity;
    private String totalPrice;
    private ItemEntityIndexDto itemEntity;
  }
}
