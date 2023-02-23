package com.hcm.backend_service.v2.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectElementException extends RuntimeException {
    public IncorrectElementException() {
        super();
    }

    public IncorrectElementException(String message) {
        super(message);
    }

    public IncorrectElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectElementException(Throwable cause) {
        super(cause);
    }

    protected IncorrectElementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
