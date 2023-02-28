package com.reggie_takeout.common;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String>  exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());
        
        if(ex.getMessage().contains("Duplicate entry")) {
            return R.error("Username already exists");
        }
        
        return R.error("Unknown error");
    }
}