package com.ldeshpande.contactbook.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ExceptionResponse {

    private String errorCode;
    private String errorMessage;

    ExceptionResponse(ErrorCode error) {
        this.errorCode = error.getCode();
        this.errorMessage = error.getMessage();
    }

}
