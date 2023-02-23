package com.prwebsitebackend.exception;

import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<Object>(new AppError(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> handleUnAuthorized(UnAuthorizedException ex, WebRequest request) {
        return new ResponseEntity<Object>(new AppError(ex.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleUnAuthorized(BadCredentialsException ex, WebRequest request) {
        return new ResponseEntity<Object>(new AppError("incorrect username or password", HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }
}
