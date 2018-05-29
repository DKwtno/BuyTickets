package com.sorahjy.buytickets.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HTTPAspect {

    private final static Logger LOGGER=  LoggerFactory.getLogger(HTTPAspect.class);


    @Pointcut("execution(public * com.sorahjy.buytickets.controller.GirlController.*(..))")
    public void log(){
    }


    @Before("log()")
    public void before(JoinPoint joinPoint){
        LOGGER.info("11111111");
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //url
        LOGGER.info("url={}",request.getRequestURL());
        LOGGER.info("method={}",request.getMethod());
        LOGGER.info("ip={}",request.getRemoteAddr());
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        LOGGER.info("args={}",joinPoint.getArgs());
    }



    @After("log()")
    public void after() {
        LOGGER.info("222222222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        LOGGER.info("response={}",object);
    }

}
