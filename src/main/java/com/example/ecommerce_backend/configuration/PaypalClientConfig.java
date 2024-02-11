package com.example.ecommerce_backend.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "paypal")
@Getter
@Setter
public class PaypalClientConfig {

  @NotEmpty private String baseUrl;

  @NotEmpty private String clientId;

  @NotEmpty private String secret;
}
