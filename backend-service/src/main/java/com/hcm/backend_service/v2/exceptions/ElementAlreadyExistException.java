package com.hcm.backend_service.v2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementAlreadyExistException extends RuntimeException {

    public ElementAlreadyExistException() {
        super();
    }

    public ElementAlreadyExistException(String message) {
        super(message);
    }

    public ElementAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected ElementAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
