package com.oocl.todoapp.advice;

import com.oocl.todoapp.exceptions.TodoNotFoundException;
import com.oocl.todoapp.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handTodoNotFoundException(TodoNotFoundException todoNotFoundException) {
        return new ErrorResponse(todoNotFoundException.getMessage(), HttpStatus.NOT_FOUND.name());
    }
}