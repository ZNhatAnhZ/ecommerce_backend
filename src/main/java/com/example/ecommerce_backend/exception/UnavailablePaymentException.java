package com.example.ecommerce_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
public class UnavailablePaymentException extends RuntimeException {
    public UnavailablePaymentException(String s) {
        super(s);
    }
}
