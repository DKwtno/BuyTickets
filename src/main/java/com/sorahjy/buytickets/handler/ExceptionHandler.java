package com.sorahjy.buytickets.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler()
    @ResponseBody
    public Result handle(Exception e){
        return null;
    }


}
