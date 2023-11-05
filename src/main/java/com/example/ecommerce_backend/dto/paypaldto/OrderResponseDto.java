package com.example.ecommerce_backend.dto.paypaldto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto implements Serializable {

	private String id;

	private String status;

	private List<LinkDto> links;

}
