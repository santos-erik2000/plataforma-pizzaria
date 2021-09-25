package com.system.application.pizzaria.exception;

import com.system.application.pizzaria.entity.enums.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.Date;


public class BebidaException extends GenericException{
    public BebidaException(ErrorType errorType, String message, Date date, HttpStatus httpStatus) {
        super(errorType,message,date, httpStatus);
    }
}





