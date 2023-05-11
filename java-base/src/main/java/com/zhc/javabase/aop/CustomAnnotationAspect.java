package com.zhc.javabase.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zhouhengchao
 * @date 2023-05-11 10:42:00
 * @version 1.0
 */
@Component
@Aspect
public class CustomAnnotationAspect {

    @Pointcut(value = "@annotation(com.zhc.javabase.aop.CustomAnnotation)")
    public void pointCut(){}

    @Pointcut(value = "execution(public * com.zhc.javabase.aop..*.*(..)))")
    public void executionPointCut(){}

    @Before("executionPointCut()")
    public void beforeHandle1(JoinPoint joinPoint){
        System.out.println("基于非注解方式，方法执行之前调用");
    }

    @Before("pointCut()")
    public void beforeHandle(JoinPoint joinPoint){
        System.out.println("基于注解方法执行之前调用");
    }

    @After("pointCut()")
    public void afterHandle(JoinPoint joinPoint){
        System.out.println("方法执行之后调用");
    }
}
