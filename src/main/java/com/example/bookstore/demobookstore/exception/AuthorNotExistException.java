package com.example.bookstore.demobookstore.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorNotExistException extends RuntimeException{

    private final String errorMessage;


    public AuthorNotExistException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
