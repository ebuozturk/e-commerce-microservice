package com.ebuozturk.productcategory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductImageNotFoundException extends NotFoundException{
    public ProductImageNotFoundException(String message) {
        super(message);
    }
}
