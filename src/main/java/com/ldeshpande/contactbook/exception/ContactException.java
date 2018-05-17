package com.ldeshpande.contactbook.exception;

import lombok.Getter;

@Getter
public class ContactException extends RuntimeException {

    private ErrorCode errorCode;

    public ContactException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
