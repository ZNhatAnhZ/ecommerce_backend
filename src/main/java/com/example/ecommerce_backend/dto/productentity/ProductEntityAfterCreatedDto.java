package com.example.ecommerce_backend.dto.productentity;

import com.example.ecommerce_backend.dto.itementity.ItemEntityAfterCreatedDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductEntityAfterCreatedDto {

  private int id;

  private String name;

  private String description;

  private List<ItemEntityAfterCreatedDto> itemEntityAfterCreatedDtoList;
}
