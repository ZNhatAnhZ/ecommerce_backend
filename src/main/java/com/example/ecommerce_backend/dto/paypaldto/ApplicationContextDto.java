package com.example.ecommerce_backend.dto.paypaldto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationContextDto implements Serializable {

  @JsonProperty("return_url")
  private String returnUrl;

  @JsonProperty("cancel_url")
  private String cancelUrl;
}
