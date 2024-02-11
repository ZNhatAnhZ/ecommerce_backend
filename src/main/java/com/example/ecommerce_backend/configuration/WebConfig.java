package com.example.ecommerce_backend.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "web")
@Getter
@Setter
public class WebConfig {

  @NotEmpty private String corsAllowedOrigins;
}
