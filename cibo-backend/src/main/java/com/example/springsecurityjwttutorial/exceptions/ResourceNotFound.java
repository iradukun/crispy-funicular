package com.example.springsecurityjwttutorial.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String msg) {
        super(msg);
    }
}
