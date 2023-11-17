package com.polarbookshop.catalogservice.errorconfig;

import com.polarbookshop.catalogservice.errorhandlers.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.errorhandlers.BookNotFoundException;
import com.polarbookshop.catalogservice.util.ErrorResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorMessage extends RuntimeException{
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponseMessage> errorResponseMessageResponseEntity(BookNotFoundException bookNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseMessage(bookNotFoundException.getMessage()));
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseMessage> errorResponseMessageResponseEntity(BookAlreadyExistsException bookAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponseMessage(bookAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> errorResponseMessageResponseEntity(MethodArgumentNotValidException methodArgumentNotValidException) {
        var errors = new HashMap<String, String>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
