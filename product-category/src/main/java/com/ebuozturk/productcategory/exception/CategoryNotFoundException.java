package com.ebuozturk.productcategory.exception;


public class CategoryNotFoundException
        extends NotFoundException
{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
