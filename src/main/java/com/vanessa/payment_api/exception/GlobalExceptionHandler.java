package com.vanessa.payment_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePaymentNotFound(
        PaymentNotFoundException exception){

            ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "PAYMENT_NOT_FOUND",
                exception.getMessage()
            );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
        }
}
