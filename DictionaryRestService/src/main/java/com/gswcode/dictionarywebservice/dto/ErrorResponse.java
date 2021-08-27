package com.gswcode.dictionarywebservice.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private String error;
    private int code;
    private String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.error = httpStatus.getReasonPhrase();
        this.code = httpStatus.value();
        this.message = message;
    }

}
