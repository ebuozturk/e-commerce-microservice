package com.ebuozturk.productcategory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProductsInStockException
        extends RuntimeException {
    public NoProductsInStockException(String message) {
        super(message);
    }
}
