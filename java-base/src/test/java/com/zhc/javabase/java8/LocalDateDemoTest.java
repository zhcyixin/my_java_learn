package com.zhc.javabase.java8;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class LocalDateDemoTest {

    @Resource
    private LocalDateDemo localDateDemo;

    @Test
    void testLocalDateTimeUse(){
        localDateDemo.localDateTimeUse();
    }
}
