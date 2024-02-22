package com.jpcchaves.parkinglotapi.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    private String path;

    public ExceptionResponse(Date timestamp,
                             String message,
                             String details,
                             String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.path = path;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
