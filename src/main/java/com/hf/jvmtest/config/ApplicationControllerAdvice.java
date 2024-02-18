package com.hf.jvmtest.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

//@ControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> badRequest(IllegalArgumentException e) {
        return createSimpleResponseEntity(e, BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> createSimpleResponseEntity(Throwable e, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("status", String.valueOf(status.value()));
        response.put("error", status.getReasonPhrase());
        response.put("timestamp", OffsetDateTime.now().toString());
        return new ResponseEntity<>(response, status);
    }
}
