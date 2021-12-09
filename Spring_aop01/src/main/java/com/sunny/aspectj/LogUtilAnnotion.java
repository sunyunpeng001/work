package com.sunny.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogUtilAnnotion {

    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void before(JoinPoint point){
        Object[] args = point.getArgs();
        System.out.println("参数的值： "+Arrays.toString(args));
        Object target = point.getTarget();
        System.out.println("被加强的对象: "+ target);
        MethodSignature signature = (MethodSignature)point.getSignature();
        System.out.println("执行的方法对应Method对象: "+signature.getMethod());
        System.out.println("加强的方法: "+signature.getName());
        System.out.println("参数名称数组： "+Arrays.asList(signature.getParameterNames()));
        System.out.println("返回值类型 ： "+signature.getReturnType());
        System.out.println("注解方法的前置通知");
    }

    @After("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void after(){
        System.out.println("注解方法的后置通知");
    }
}
