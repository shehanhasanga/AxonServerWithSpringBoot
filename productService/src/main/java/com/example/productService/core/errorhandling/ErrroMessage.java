package com.example.productService.core.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrroMessage {
    private Date timestamp;
    private String message;
}
