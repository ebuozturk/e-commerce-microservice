package com.ebuozturk.productcategory.exception;


public class ProductNotFoundException
        extends NotFoundException
{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
