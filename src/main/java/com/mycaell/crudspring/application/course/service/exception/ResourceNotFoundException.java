package com.mycaell.crudspring.application.course.service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found!");
    }

    public ResourceNotFoundException(String resourceName) {
        super(resourceName + " not found!");
    }
}
