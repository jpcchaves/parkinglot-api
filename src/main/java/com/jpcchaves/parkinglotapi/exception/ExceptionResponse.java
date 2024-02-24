package com.jpcchaves.parkinglotapi.exception;

import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String path;
    private Map<String, String> errors;

    public ExceptionResponse(Date timestamp,
                             String message,
                             String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
    }

    public ExceptionResponse(Date timestamp,
                             String message,
                             String path,
                             BindingResult result
    ) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        addErrors(result);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    private void addErrors(BindingResult result) {
        this.errors = new HashMap<>();

        result.getFieldErrors()
                .forEach(fieldError -> {
                    this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });
    }
}
