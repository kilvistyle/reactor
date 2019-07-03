package com.example.reactor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

public class AppException extends ResponseStatusException {

    public AppException(@NotNull String message, @NotNull HttpStatus status) {
        super(status, message);
    }

    public AppException(@NotNull String message, @NotNull HttpStatus status, @NotNull Exception exception) {
        super(status, message, exception);
    }

}
