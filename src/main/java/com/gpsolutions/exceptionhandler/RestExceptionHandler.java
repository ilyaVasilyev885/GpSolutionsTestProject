package com.gpsolutions.exceptionhandler;

import com.gpsolutions.exceptions.HotelNotFoundException;
import org.hibernate.JDBCException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionHandlerMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        String message = exc.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .findFirst()
                .orElse(exc.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), message);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionHandler(HandlerMethodValidationException exc) {
        String message = exc.getAllValidationResults().stream()
                .flatMap(exception -> exception.getResolvableErrors().stream())
                .map(MessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exc.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), message);
    }

    @ExceptionHandler(JDBCException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionHandelrJDBCException(JDBCException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), exception.getErrorMessage());
    }

    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionHandlerHotelNotFoundException(HotelNotFoundException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), exception.getMessage());
    }
}
