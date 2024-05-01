package com.example.ecommerce_backend.dto.variationentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link com.example.ecommerce_backend.model.VariationEntity} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariationEntityFlatIndexDto implements Serializable {
  private int id;
  private String name;
  private String value;
  private Integer parentVariationEntityId;
}
