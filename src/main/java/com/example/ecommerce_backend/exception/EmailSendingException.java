package com.example.ecommerce_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
public class EmailSendingException extends RuntimeException {

	public EmailSendingException(String s) {
		super(s);
	}

}
