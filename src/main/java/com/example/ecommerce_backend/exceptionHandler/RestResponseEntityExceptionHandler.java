package com.example.ecommerce_backend.exceptionHandler;

import com.example.ecommerce_backend.exception.InvalidCredentialException;
import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidCredentialException.class)
    public void handleInvalidCredentialException(InvalidCredentialException e, HttpServletResponse response) throws RuntimeException, IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
