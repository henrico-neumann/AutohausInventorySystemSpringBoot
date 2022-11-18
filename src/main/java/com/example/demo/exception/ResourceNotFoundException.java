package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

//Whenever a record does not exist in the database the api will throw the exception ResourceNotFoundException and will respond with a NOT_Found http status
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //error message from CarController.java ("Car by id" + id + "not found")
    public ResourceNotFoundException(String message) {
        super(message);
    }


}
