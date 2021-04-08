package com.guli.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.failed().massage("异常");
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R errorO(ArithmeticException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.failed().massage("ArithmeticException异常");
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(GuliException.class)
    @ResponseBody
    public R errorI(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.failed().code(e.getCode()).massage(e.getMessage());
    }





}
