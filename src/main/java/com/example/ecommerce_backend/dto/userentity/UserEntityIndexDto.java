package com.example.ecommerce_backend.dto.userentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntityIndexDto {

	private int id;

	private String username;

	private String firstName;

	private String lastName;

	private String telephone;

}
