package com.jpcchaves.parkinglotapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.logging.Logger;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler {
    static Logger logger = Logger.getLogger(CustomizedExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleUnexpectedExceptions(Exception ex,
                                                                              WebRequest request) {
        logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
