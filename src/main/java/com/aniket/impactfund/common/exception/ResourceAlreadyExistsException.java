package com.aniket.impactfund.common.exception;

import lombok.Getter;

public class ResourceAlreadyExistsException extends RuntimeException {
    @Getter
    private final String field;
    private final String message;

    public ResourceAlreadyExistsException(String message, String field) {
        super(field + " already exists");

        this.field = field;
        this.message = message;
    }

    public String getDetailedMessage() {
        return message + " " + field;
    }
}
