package com.example.ecommerce_backend.model.dto.paypalbusinessaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link com.example.ecommerce_backend.model.entity.PaypalBusinessAccountEntity} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaypalBusinessAccountIndexDto implements Serializable {
  private Integer id;
  private String email;
  private String clientId;
  private String clientSecret;
  private Boolean isEnable;
}
