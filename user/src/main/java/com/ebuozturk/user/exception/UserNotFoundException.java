package com.ebuozturk.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends NotFoundException{

    public UserNotFoundException(String message){
        super(message);
    }
}

