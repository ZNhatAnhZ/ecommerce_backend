package com.example.ecommerce_backend.dto.PaypalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class LinkDto implements Serializable {
    private String href;
    private String rel;
    private String method;
}
