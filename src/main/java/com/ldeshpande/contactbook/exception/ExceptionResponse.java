package com.ldeshpande.contactbook.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ExceptionResponse {

    private String code;
    private String message;

    ExceptionResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}
