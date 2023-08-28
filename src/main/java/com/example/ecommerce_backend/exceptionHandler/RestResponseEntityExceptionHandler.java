package com.example.ecommerce_backend.exceptionHandler;

import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceDuplicateException.class)
    public void handleConflict(ResourceDuplicateException e) throws RuntimeException {
        throw e;
    }
}
