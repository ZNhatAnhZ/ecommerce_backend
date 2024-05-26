package com.example.ecommerce_backend.exception.exceptionhandler;

import com.example.ecommerce_backend.exception.InvalidCredentialException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidCredentialException.class)
  public void handleInvalidCredentialException(
      InvalidCredentialException e, HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }
}
