package io.cloud.home.exception;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class GlobalErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String error;
    private final String message;

    public GlobalErrorResponse(Throwable error) {
        this.error = error.getClass().getName();
        this.message = error.getMessage();
    }

}