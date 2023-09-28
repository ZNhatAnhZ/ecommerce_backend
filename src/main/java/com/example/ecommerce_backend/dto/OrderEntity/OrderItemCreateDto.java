package com.example.ecommerce_backend.dto.OrderEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link com.example.ecommerce_backend.model.OrderItemEntity}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemCreateDto implements Serializable {
    private int userId;
    private Integer cartItemEntityId;
    private int quantity;
    private int itemEntityId;
    private String itemEntitySku;
}