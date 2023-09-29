package com.example.kommuner.exception;


public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionId = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }


}
