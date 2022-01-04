package com.nnk.springboot.Exceptions.CustomExceptions;

public class ObjectNotFoundException extends RuntimeException{
    String object;
    Integer id;

    public ObjectNotFoundException(String object, Integer id) {
        super(object + "not found for id : "+id);
        this.object = object;
        this.id = id;
    }
}
