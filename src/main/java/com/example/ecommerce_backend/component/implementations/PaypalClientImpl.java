package com.example.ecommerce_backend.component.implementations;

import com.example.ecommerce_backend.component.interfaces.PaypalClientInterface;
import com.example.ecommerce_backend.configuration.PaypalClientConfig;
import com.example.ecommerce_backend.constant.PaypalEndpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

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
    public WebClient.ResponseSpec sendGetAccessToken() throws URISyntaxException {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", paypalClientConfig.getClientId());
        requestBody.add("client_secret", paypalClientConfig.getSecret());
        requestBody.add("grant_type", "client_credentials");


        return webClient.post()
                .uri(new URI(PaypalEndpoints.ACCESS_TOKEN_URL.getPath()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(requestBody))
                .retrieve();
    }

    @Override
    public WebClient.ResponseSpec sendCreateOrder() throws URISyntaxException {
        return null;
    }

    @Override
    public WebClient.ResponseSpec sendCaptureOrder() throws URISyntaxException {
        return null;
    }
}
