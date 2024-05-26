package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.model.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.model.dto.paypaldto.AccessTokenResponseDto;
import com.example.ecommerce_backend.model.dto.paypaldto.OrderDto;
import com.example.ecommerce_backend.service.interfaces.PaypalClientInterface;
import com.example.ecommerce_backend.util.constant.PaypalEndpoints;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaypalClientImpl implements PaypalClientInterface {

  private final WebClient paypalWebClient;

  @Value("${paypal-client-id}")
  private final String clientId;

  @Value("${paypal-client-secret}")
  private final String clientSecret;

  @Override
  public WebClient.ResponseSpec sendGetAccessToken() {
    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("grant_type", "client_credentials");

    return paypalWebClient
        .post()
        .uri(PaypalEndpoints.ACCESS_TOKEN_URL.getPath())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .accept(MediaType.APPLICATION_JSON)
        .headers(httpHeaders -> httpHeaders.setBasicAuth(clientId, clientSecret))
        .bodyValue(requestBody)
        .retrieve();
  }

  @Override
  public WebClient.ResponseSpec sendCreateOrder(
      AccessTokenResponseDto accessTokenResponseDto, OrderDto orderDto) {
    return paypalWebClient
        .post()
        .uri(PaypalEndpoints.CREATE_ORDER_URL.getPath())
        .contentType(MediaType.APPLICATION_JSON)
        .header(
            HttpHeaders.AUTHORIZATION,
            String.format(
                "%s %s",
                accessTokenResponseDto.getTokenType(), accessTokenResponseDto.getAccessToken()))
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(orderDto)
        .retrieve();
  }

  @Override
  public WebClient.ResponseSpec sendCaptureOrder(
      AccessTokenResponseDto accessTokenResponseDto, UserPayOrderDto userPayOrderDto) {
    return paypalWebClient
        .post()
        .uri(PaypalEndpoints.CAPTURE_ORDER_URL.getPath(), userPayOrderDto.getPaypalOrderId())
        .contentType(MediaType.APPLICATION_JSON)
        .header(
            HttpHeaders.AUTHORIZATION,
            String.format(
                "%s %s",
                accessTokenResponseDto.getTokenType(), accessTokenResponseDto.getAccessToken()))
        .accept(MediaType.APPLICATION_JSON)
        .retrieve();
  }
}
