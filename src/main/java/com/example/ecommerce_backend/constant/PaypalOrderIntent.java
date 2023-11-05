package com.example.ecommerce_backend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaypalOrderIntent {

	CAPTURE("CAPTURE"), AUTHORIZE("AUTHORIZE");

	private final String value;

}
