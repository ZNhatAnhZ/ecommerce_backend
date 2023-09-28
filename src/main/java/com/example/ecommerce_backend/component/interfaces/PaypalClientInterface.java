package com.example.ecommerce_backend.component.interfaces;

import com.example.ecommerce_backend.dto.OrderEntity.UserPayOrderDto;
import com.example.ecommerce_backend.dto.PaypalDTO.AccessTokenResponseDto;
import com.example.ecommerce_backend.dto.PaypalDTO.OrderDto;
import org.springframework.web.reactive.function.client.WebClient;

public interface PaypalClientInterface {
    WebClient.ResponseSpec sendGetAccessToken();
    WebClient.ResponseSpec sendCreateOrder(AccessTokenResponseDto accessTokenResponseDto, OrderDto orderDto);
    WebClient.ResponseSpec sendCaptureOrder(AccessTokenResponseDto accessTokenResponseDto, UserPayOrderDto userPayOrderDto);
}
