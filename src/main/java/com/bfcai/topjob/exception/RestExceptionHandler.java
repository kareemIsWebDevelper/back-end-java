package com.bfcai.topjob.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(value = HttpStatus.CONFLICT,reason = "Conflict Data Found")
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public void DataConflictException(){

    }
}
