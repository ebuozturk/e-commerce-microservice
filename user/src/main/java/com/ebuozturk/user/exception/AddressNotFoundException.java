package com.ebuozturk.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AddressNotFoundException extends NotFoundException{

    public AddressNotFoundException(String message) {
        super(message);
    }
}
