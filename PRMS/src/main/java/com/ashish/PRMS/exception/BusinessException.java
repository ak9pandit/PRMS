package com.ashish.PRMS.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class BusinessException extends RuntimeException{

    public BusinessException(String msg){
        super(msg);
    }

}
