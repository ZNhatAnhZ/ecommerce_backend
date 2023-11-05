package com.example.ecommerce_backend.dto.itementity;

import com.example.ecommerce_backend.dto.productentity.ProductEntityIndexDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ItemEntityIndexDto implements Serializable {

	private int id;

	private String price;

	private String stock;

	private boolean isDisabled;

	private ProductEntityIndexDto productEntity;

}
