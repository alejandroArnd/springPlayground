package com.apispring.apispring.infrastructure.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
     public ResponseEntity<Object> notValidRequestData(HttpMessageNotReadableException httpMessageNotReadableException){
        String message = this.createMessageError(httpMessageNotReadableException);
        Map<String, String> json = new HashMap<>();
        json.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        json.put("message", message);
        return ResponseEntity.badRequest().body(json);
    }

    public String createMessageError(HttpMessageNotReadableException httpMessageNotReadableException){
        Throwable cause = httpMessageNotReadableException.getRootCause();
        String field = null;
        String expectedValue = null;
        String gotValue = null;
        if (cause instanceof InvalidFormatException) {
            field  = ((InvalidFormatException) cause).getPath().get(0).getFieldName();
            expectedValue = ((InvalidFormatException) cause).getTargetType().getSimpleName();
            gotValue = ((InvalidFormatException) cause).getValue().getClass().getSimpleName();
        }
        String message = field == null ?
                cause.getMessage():
                String.format("Invalid value for %s. Expected value of type %s but got: %s", field, expectedValue, gotValue);

        return message;
    }
}
