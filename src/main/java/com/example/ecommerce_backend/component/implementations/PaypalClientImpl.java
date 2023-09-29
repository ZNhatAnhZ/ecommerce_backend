package com.example.ecommerce_backend.component.implementations;

import com.example.ecommerce_backend.component.interfaces.PaypalClientInterface;
import com.example.ecommerce_backend.configuration.PaypalClientConfig;
import com.example.ecommerce_backend.constant.PaypalEndpoints;
import com.example.ecommerce_backend.dto.OrderEntity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.PaypalDTO.AccessTokenResponseDto;
import com.example.ecommerce_backend.dto.PaypalDTO.OrderDto;
import com.example.ecommerce_backend.exception.UnavailablePaymentException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@Component
@Slf4j
public class PaypalClientImpl implements PaypalClientInterface {
    private final WebClient webClient;
    private final PaypalClientConfig paypalClientConfig;

    @Autowired
    public PaypalClientImpl(WebClient.Builder webClientBuilder, PaypalClientConfig paypalClientConfig) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8000").build();
        this.paypalClientConfig = paypalClientConfig;
    }

    @Override
    public WebClient.ResponseSpec sendGetAccessToken(){
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");

        String authentication = paypalClientConfig.getClientId() + ":" + paypalClientConfig.getSecret();

        try {
            return webClient.post()
                    .uri(new URI(paypalClientConfig.getBaseUrl() + PaypalEndpoints.ACCESS_TOKEN_URL.getPath()))
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Basic " + Base64.getEncoder().encodeToString(authentication.getBytes()))
                    .bodyValue(requestBody)
                    .retrieve();
        } catch (URISyntaxException e) {
            throw new UnavailablePaymentException("Paypal API url error");
        }
    }

    @Override
    public WebClient.ResponseSpec sendCreateOrder(AccessTokenResponseDto accessTokenResponseDto, OrderDto orderDto){
        try {
            log.error(new ObjectMapper().writeValueAsString(orderDto));
        } catch (JsonProcessingException e) {
            throw new UnavailablePaymentException("Error when parsing order");
        }

        try {
            return webClient.post()
                    .uri(new URI(paypalClientConfig.getBaseUrl() + PaypalEndpoints.CREATE_ORDER_URL.getPath()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, accessTokenResponseDto.getTokenType() + " " + accessTokenResponseDto.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(orderDto)
                    .retrieve();
        } catch (URISyntaxException e) {
            throw new UnavailablePaymentException("Paypal API url error");
        }
    }

    @Override
    public WebClient.ResponseSpec sendCaptureOrder(AccessTokenResponseDto accessTokenResponseDto, UserPayOrderDto userPayOrderDto){
        try {
            return webClient.post()
                    .uri(new URI(paypalClientConfig.getBaseUrl() + PaypalEndpoints.CAPTURE_ORDER_URL.getPath().replace(PaypalEndpoints.ORDER_ID_VAR.getPath(), userPayOrderDto.getPaypalOrderId())))
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, accessTokenResponseDto.getTokenType() + " " + accessTokenResponseDto.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve();
        } catch (URISyntaxException e) {
            throw new UnavailablePaymentException("Paypal API url error");
        }
    }
}
