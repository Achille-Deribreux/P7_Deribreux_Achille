package com.nnk.springboot.Exceptions.CustomExceptions;

public class ObjetNotFoundExceptionString extends RuntimeException{
    String object;
    String searchString;

    public ObjetNotFoundExceptionString(String object, String searchString) {
        super(object + "not found for "+searchString);
        this.object = object;
        this.searchString = searchString;
    }
}
