package com.ldeshpande.contactbook.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String exceptionStackTrace;

    public ContactException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.exceptionStackTrace = "";
    }

    public ContactException(ErrorCode errorCode, String exceptionStackTrace) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.exceptionStackTrace = exceptionStackTrace;
    }

}
