package com.ashish.PRMS.exception;

import com.ashish.PRMS.auth.dto.ErrorMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> BusinessExceptionHandler(Exception ex){

        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setMsg(ex.getMessage());
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

}
