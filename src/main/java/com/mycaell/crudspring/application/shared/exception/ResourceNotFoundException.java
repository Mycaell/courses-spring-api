package com.mycaell.crudspring.application.shared.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found!");
    }

    public ResourceNotFoundException(String resourceName) {
        super(resourceName + " not found!");
    }
}
