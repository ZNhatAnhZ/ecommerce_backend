package com.example.ecommerce_backend.model.dto.variationentity;

import com.example.ecommerce_backend.model.entity.VariationEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link VariationEntity} */
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
  private List<Integer> childrenVariationEntityIdList;
}
