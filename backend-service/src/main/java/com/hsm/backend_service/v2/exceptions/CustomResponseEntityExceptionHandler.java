package com.hcm.backend_service.v2.exceptions;

import com.hcm.backend_service.v2.payload.ExceptionPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionPayload> handleUserNotFound(UsernameNotFoundException exception, WebRequest request) {
        ExceptionPayload payload =
                new ExceptionPayload("incorrect username or password", HttpStatus.UNAUTHORIZED);
        return
                new ResponseEntity<>(payload, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionPayload> handleNotFound(ElementNotFoundException exception, WebRequest request) {
        ExceptionPayload payload = new ExceptionPayload(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ElementAlreadyExistException.class)
    public ResponseEntity<ExceptionPayload> handleAlreadyExist(
            ElementAlreadyExistException exception,
            WebRequest request
    ){
        ExceptionPayload payload =
                new ExceptionPayload(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IncorrectElementException.class)
    public ResponseEntity<ExceptionPayload> handleIncorrect(
            IncorrectElementException exception,
            WebRequest request
    ){
        ExceptionPayload payload =
                new ExceptionPayload(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionPayload> handleBadRequest(
            BadRequestException exception,
            WebRequest request
    ){
        ExceptionPayload payload =
                new ExceptionPayload(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public final ResponseEntity<Object> handleEmailAlreadyExists(EmailAlreadyExistsException ex, WebRequest request) {
        EmailAlreadyExistsResponse exceptionResponse = new EmailAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionPayload> handleForbiddenException(
            ForbiddenException exception,
            WebRequest request
    ){
        ExceptionPayload payload =
                new ExceptionPayload(exception.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(payload, HttpStatus.FORBIDDEN);
    }
}
