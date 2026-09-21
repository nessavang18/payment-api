package com.vanessa.payment_api.exception;

public record ErrorResponseDto(
        int status,
        String error,
        String message
) {
}
