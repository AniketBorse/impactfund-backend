package com.aniket.impactfund.common.exception;

public class DuplicateResourceException extends RuntimeException {

    private final String resource;
    private final String field;
    private final Object value;

    public DuplicateResourceException(
            String resource,
            String field,
            Object value
    ) {
        super(resource + " already exists");
        this.resource = resource;
        this.field = field;
        this.value = value;
    }

    public String getResource() {
        return resource;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public String getDetailedMessage() {
        return resource + " with " + field + " '" + value + "' already exists.";
    }

}
