package com.example.ecommerce_backend.dto.productentity;

import com.example.ecommerce_backend.dto.itementity.ItemEntityAfterCreatedDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductEntityAfterCreatedDto {

	private int id;

	private String name;

	private String description;

	private List<ItemEntityAfterCreatedDto> itemEntityAfterCreatedDtoList;

}
