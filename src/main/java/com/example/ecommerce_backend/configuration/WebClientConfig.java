package com.example.ecommerce_backend.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

  private final WebClient.Builder webClientBuilder;

  @Value("${paypal-base-url}")
  private final String paypalBaseUrl;

  @Bean
  public WebClient paypalWebClient() {
    return webClientBuilder.baseUrl(paypalBaseUrl).build();
  }
}
