package com.apibiblioteca.api_biblioteca.resources.exception;

import com.apibiblioteca.api_biblioteca.Services.exception.InvalidQuantityException;
import com.apibiblioteca.api_biblioteca.Services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExeptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<StandardError> InvalidQuantityException(InvalidQuantityException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "invalid quantity", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
