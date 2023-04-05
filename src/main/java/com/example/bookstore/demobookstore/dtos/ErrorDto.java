package com.example.bookstore.demobookstore.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ErrorDto implements Serializable {
    private String errorMessage;
    private String errorCode;
}
