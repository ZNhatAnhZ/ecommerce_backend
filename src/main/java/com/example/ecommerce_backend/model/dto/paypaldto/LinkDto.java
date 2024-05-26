package com.example.ecommerce_backend.model.dto.paypaldto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class LinkDto implements Serializable {

  private String href;

  private String rel;

  private String method;
}
