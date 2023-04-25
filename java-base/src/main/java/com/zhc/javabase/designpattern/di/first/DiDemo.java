package com.zhc.javabase.designpattern.di.first;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/bean.xml");
        RateLimiter rateLimiter = (RateLimiter)applicationContext.getBean("rateLimiter");
        rateLimiter.test();
    }
}
