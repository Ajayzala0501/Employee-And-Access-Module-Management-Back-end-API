package com.practicaltask.employeeaccess.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Exception Handler for ResourceNotFoundException
     * Handles exceptions of type ResourceNotFoundException and returns an error response with a 404 (Not Found) status.
     *
     * @param ex The exception instance of type ResourceNotFoundException.
     * @return An ErrorMessage object containing the HTTP status code and error message.
     */
    @ExceptionHandler(ResourceNotFoundExpection.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundExceptions(ResourceNotFoundExpection ex){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    /**
     * Global Exception Handler
     * Handles exceptions of type Exception and returns an error response with a 500 (Internal Server Error) status.
     *
     * @param ex The exception instance of type Exception.
     * @return An ErrorMessage object containing the HTTP status code and error message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
