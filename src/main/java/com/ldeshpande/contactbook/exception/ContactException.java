package com.ldeshpande.contactbook.exception;

import lombok.Getter;

@Getter
public class ContactException extends RuntimeException {

    private String message;

    public ContactException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        message = errorCode.getMessage();
    }

}