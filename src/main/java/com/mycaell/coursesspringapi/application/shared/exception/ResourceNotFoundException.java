package com.mycaell.coursesspringapi.application.shared.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found!");
    }

    public ResourceNotFoundException(String resourceName) {
        super(resourceName + " not found!");
    }
}
