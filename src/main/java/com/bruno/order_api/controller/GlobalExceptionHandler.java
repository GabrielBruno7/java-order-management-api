package com.bruno.order_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bruno.order_api.domain.order.OrderNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Void> handleOrderNotFound(OrderNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}