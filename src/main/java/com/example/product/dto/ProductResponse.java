package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ProductResponse<T> {
    private String message;
    private T payload;
    private HttpStatus httpStatus;
}