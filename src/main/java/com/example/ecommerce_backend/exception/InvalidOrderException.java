package com.example.ecommerce_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidOrderException extends RuntimeException {

	public InvalidOrderException(String s) {
		super(s);
	}

}
