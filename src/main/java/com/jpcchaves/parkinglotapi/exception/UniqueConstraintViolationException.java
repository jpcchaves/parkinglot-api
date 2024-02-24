package com.jpcchaves.parkinglotapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UniqueConstraintViolationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5309580091053936166L;

    public UniqueConstraintViolationException(String message) {
        super(message);
    }
}
