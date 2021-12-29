package com.nnk.springboot.Exceptions.CustomExceptions;

public class BidListNotFoundException extends RuntimeException{
    String detail;
    public BidListNotFoundException(String detail) {
        super("BidList not found " + detail);
        this.detail = detail;
    }
}
