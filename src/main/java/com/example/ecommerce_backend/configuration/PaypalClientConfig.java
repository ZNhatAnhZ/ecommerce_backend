package com.example.ecommerce_backend.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "paypal")
@Getter
public class PaypalClientConfig {
    @NotEmpty
    private String baseUrl;
    @NotEmpty
    private String clientId;
    @NotEmpty
    private String secret;
}
