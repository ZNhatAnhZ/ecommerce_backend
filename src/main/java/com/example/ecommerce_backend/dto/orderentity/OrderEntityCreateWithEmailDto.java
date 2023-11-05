package com.example.ecommerce_backend.dto.orderentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.ecommerce_backend.model.OrderEntity}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEntityCreateWithEmailDto implements Serializable {

	private String email;

	private List<OrderItemEntityCreateDto> itemList;

}