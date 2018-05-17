package com.ldeshpande.contactbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionAdvice {

    @ExceptionHandler(ContactException.class)
    public ResponseEntity<ExceptionResponse> handleContactExceptions(final ContactException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorCode);

        switch (errorCode) {
            case CONTACT_NOT_FOUND:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
        }
    }

}
