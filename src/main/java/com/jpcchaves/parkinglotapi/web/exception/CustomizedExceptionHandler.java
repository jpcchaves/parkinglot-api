package com.jpcchaves.parkinglotapi.web.exception;

import com.jpcchaves.parkinglotapi.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.logging.Logger;

@RestControllerAdvice
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                         HttpServletRequest request,
                                                                                         BindingResult result) {
        logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Campos invalidos!",
                request.getRequestURI(), result);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UniqueConstraintViolationException.class, CpfUniqueViolationException.class})
    public final ResponseEntity<ExceptionResponse> handleUniqueConstraintViolationException(UniqueConstraintViolationException ex,
                                                                                            WebRequest request
    ) {
        logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex,
                                                                                 WebRequest request
    ) {
        logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public final ResponseEntity<ExceptionResponse> handleEntityNotFoundException(PasswordInvalidException ex,
                                                                                 WebRequest request
    ) {
        logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException ex,
                                                                               WebRequest request
    ) {
        logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ParkingSpaceCodeUniqueViolationException.class)
    public final ResponseEntity<ExceptionResponse> handleParkingSpaceCodeUniqueViolationException(ParkingSpaceCodeUniqueViolationException ex,
                                                                                                  WebRequest request
    ) {
        logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
