package com.ebuozturk.basket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BasketProductNotFoundException extends NotFoundException {

    public BasketProductNotFoundException(String message) {
        super(message);
    }
}

