package com.example.ecommerce_backend.component.interfaces;

import com.example.ecommerce_backend.dto.orderentity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.paypaldto.AccessTokenResponseDto;
import com.example.ecommerce_backend.dto.paypaldto.OrderDto;
import org.springframework.web.reactive.function.client.WebClient;

public interface PaypalClientInterface {

  WebClient.ResponseSpec sendGetAccessToken();

  WebClient.ResponseSpec sendCreateOrder(
      AccessTokenResponseDto accessTokenResponseDto, OrderDto orderDto);

  WebClient.ResponseSpec sendCaptureOrder(
      AccessTokenResponseDto accessTokenResponseDto, UserPayOrderDto userPayOrderDto);
}
