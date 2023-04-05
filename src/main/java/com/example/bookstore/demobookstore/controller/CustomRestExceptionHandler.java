package com.example.bookstore.demobookstore.controller;

import com.example.bookstore.demobookstore.dtos.ErrorDto;
import com.example.bookstore.demobookstore.exception.AuthorNotExistException;
import com.example.bookstore.demobookstore.exception.MyConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler{

    @ExceptionHandler(MyConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleException (MyConstraintViolationException ex){
        var errorDto = new ErrorDto();
        errorDto.setErrorMessage(ex.getErrorMessage());
        errorDto.setErrorCode("8001");
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorNotExistException.class)
    public ResponseEntity<ErrorDto> handleException (AuthorNotExistException ex){
        var errorDto = new ErrorDto();
        errorDto.setErrorMessage(ex.getErrorMessage());
        errorDto.setErrorCode("8002");
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}