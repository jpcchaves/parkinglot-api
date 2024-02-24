package com.jpcchaves.parkinglotapi.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8564632208248852987L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
