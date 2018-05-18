package com.ldeshpande.contactbook.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionAdvice {

    @ExceptionHandler(ContactException.class)
    public ResponseEntity<ExceptionResponse> handleContactExceptions(final ContactException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorCode);

        log.error(String.valueOf(errorCode), exception.getExceptionStackTrace());

        switch (errorCode) {
            case CONTACT_NOT_FOUND:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
            case CONTACT_NOT_SAVED:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
        }
    }

}
