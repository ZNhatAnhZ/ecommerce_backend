package com.example.ecommerce_backend.dto.productcategoryentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryEntityCreateDto {

	String name;

	String description;

}
