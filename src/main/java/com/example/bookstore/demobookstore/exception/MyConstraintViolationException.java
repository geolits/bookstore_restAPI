package com.example.bookstore.demobookstore.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyConstraintViolationException extends RuntimeException{

    private final String errorMessage;


    public MyConstraintViolationException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
