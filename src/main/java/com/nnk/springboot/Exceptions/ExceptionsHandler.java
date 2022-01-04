package com.nnk.springboot.Exceptions;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.Exceptions.CustomExceptions.ObjetNotFoundExceptionString;
import com.nnk.springboot.domain.CustomErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    private static final Logger logger = LogManager.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleBidListNotFoundException(ObjectNotFoundException e){
        logger.error(e.getMessage());
        CustomErrorResponse res = new CustomErrorResponse(e.getMessage(),e, HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjetNotFoundExceptionString.class)
    public ResponseEntity<Object> handleObjetNotFoundExceptionString(ObjetNotFoundExceptionString e){
        logger.error(e.getMessage());
        CustomErrorResponse res = new CustomErrorResponse(e.getMessage(),e, HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

}

