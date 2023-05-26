package com.test.dog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dog not found")
public class DataDogNotFound extends RuntimeException{
    private static final long serialVersionUID = 5166941534727054612L;

    public DataDogNotFound(){}
    public DataDogNotFound(String message) {
        super(message);
    }
}
