package com.example.ecommerce_backend.model.dto.paypalbusinessaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
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
public class PaypalBusinessAccountCreateDto implements Serializable {

  @NotNull(message = "Email is required")
  private String email;

  @NotNull(message = "Client ID is required")
  private String clientId;

  @NotNull(message = "Client secret is required")
  private String clientSecret;

  private Boolean isEnabled = true;

}
