package com.zhc.javabase.aop.stat;

import org.junit.jupiter.api.Test;

/**
 * 测试类
 */
public class CarAgentTest {
    @Test
    void testDrive(){
        CarAgent carAgent = new CarAgent();
        carAgent.drive();
    }
}
