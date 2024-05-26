package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.exception.UnavailablePaymentException;
import com.example.ecommerce_backend.model.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.model.dto.paypaldto.AccessTokenResponseDto;
import com.example.ecommerce_backend.model.dto.paypaldto.OrderDto;
import com.example.ecommerce_backend.model.entity.PaypalBusinessAccountEntity;
import com.example.ecommerce_backend.service.interfaces.PaypalBusinessAccountInterface;
import com.example.ecommerce_backend.service.interfaces.PaypalClientInterface;
import com.example.ecommerce_backend.util.constant.PaypalEndpoints;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaypalClientImpl implements PaypalClientInterface {

  private final WebClient paypalWebClient;

  private final PaypalBusinessAccountInterface paypalBusinessAccountInterface;

  @Override
  public WebClient.ResponseSpec sendGetAccessToken() {
    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("grant_type", "client_credentials");
    PaypalBusinessAccountEntity paypalBusinessAccountEntity =
        paypalBusinessAccountInterface.getOldestEnabledPaypalBusinessAccount();

    return paypalWebClient
        .post()
        .uri(PaypalEndpoints.ACCESS_TOKEN_URL.getPath())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .accept(MediaType.APPLICATION_JSON)
        .headers(
            httpHeaders ->
                httpHeaders.setBasicAuth(
                    paypalBusinessAccountEntity.getClientId(),
                    paypalBusinessAccountEntity.getClientSecret()))
        .bodyValue(requestBody)
        .retrieve()
        .onStatus(
            HttpStatusCode::isError,
            clientResponse -> {
              log.error(
                  "Error while getting access token from paypal with clientId: {}, clientSecret: {}, and response: {}",
                  paypalBusinessAccountEntity.getClientId(),
                  paypalBusinessAccountEntity.getClientSecret(),
                  clientResponse);
              return Mono.error(
                  new UnavailablePaymentException("Paypal payment function is not available"));
            });
  }

  @Override
  public WebClient.ResponseSpec sendCreateOrder(
      @NotNull AccessTokenResponseDto accessTokenResponseDto, OrderDto orderDto) {
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
        .retrieve()
        .onStatus(
            HttpStatusCode::isError,
            clientResponse -> {
              log.error(
                  "Error while creating orders from paypal with response: {}", clientResponse);
              return Mono.error(
                  new UnavailablePaymentException("Paypal payment function is not available"));
            });
  }

  @Override
  public WebClient.ResponseSpec sendCaptureOrder(
      @NotNull AccessTokenResponseDto accessTokenResponseDto,
      @NotNull UserPayOrderDto userPayOrderDto) {
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
        .retrieve()
        .onStatus(
            HttpStatusCode::isError,
            clientResponse -> {
              log.error("Error while capture orders from paypal with response: {}", clientResponse);
              return Mono.error(
                  new UnavailablePaymentException("Paypal payment function is not available"));
            });
  }
}
