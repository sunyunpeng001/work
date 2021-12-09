package com.sunny.aspectj;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogUtil {

    /**
     * 前置通知
     */
    @Before("execution(* com.sunny.service..*.*(..))")
    public void before(){
        System.out.println("前置方法通知");
    }

    /**
     * 后置通知
     */
    @After("execution(* com.sunny.service..*.*(..))")
    public void after(){
        System.out.println("后置方法通知");
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("execution(* com.sunny.service..*.*(..))")
    public void afterExcetion(){
        System.out.println("后置方法的异常通知");
    }

    /**
     * 后置返回通知
     */
    @AfterReturning("execution(* com.sunny.service..*.*(..))")
    public void afeterReturn(){
        System.out.println("后置方法返回通知");

    }

}
