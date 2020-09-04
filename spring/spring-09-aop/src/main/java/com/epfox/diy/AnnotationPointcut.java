package com.epfox.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//第三种方式 使用注解实现
@Aspect
public class AnnotationPointcut {

    @Before("execution(* com.epfox.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("========方法执行前========");
    }
    @After("execution(* com.epfox.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("========方法执行后========");
    }
    @Around("execution(* com.epfox.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");

//        Signature signature = jp.getSignature();//获得签名
//        System.out.println("signature:"+signature);
        //执行方法
        Object proceed = jp.proceed();

        System.out.println("环绕后");

//        System.out.println("proceed"+proceed);
    }
}
