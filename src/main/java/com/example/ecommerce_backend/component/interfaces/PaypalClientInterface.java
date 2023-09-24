package com.example.ecommerce_backend.component.interfaces;

import org.springframework.web.reactive.function.client.WebClient;

import java.net.URISyntaxException;

public interface PaypalClientInterface {
    WebClient.ResponseSpec sendGetAccessToken() throws URISyntaxException;
    WebClient.ResponseSpec sendCreateOrder() throws URISyntaxException;
    WebClient.ResponseSpec sendCaptureOrder() throws URISyntaxException;
}
