package com.ldeshpande.contactbook.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {

    UNKNOWN_ERROR("CB-000", "Unknown error, please contact the developer"),
    CONTACT_NOT_FOUND("CB-001", "Contact not found, please try again"),
    CONTACT_NOT_SAVED("CB-002", "Failed to save the contact, please try again"),
    CONTACT_NOT_DELETED("CB-003", "Failed to delete the contact, please try again"),
    CONTACT_NOT_UPDATED("CB-004", "Failed to update the contact, please try again");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
