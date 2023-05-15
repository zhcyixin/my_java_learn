package com.zhc.javabase.aop.dyn;

import com.zhc.javabase.aop.CarDriver;
import com.zhc.javabase.aop.Driver;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynAgentTest {

    @Test
    void testJdkAgent1(){
        Driver carDriver = new CarDriver();
        Driver proxyCarDriver = (Driver)Proxy.newProxyInstance(carDriver.getClass().getClassLoader(),
                carDriver.getClass().getInterfaces(),new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //args表示有几个参数，这里只有一个参数
                System.out.println("动态代理-开车前记得加油");
                //这里调用真实对象的方法
                return method.invoke(carDriver, args);
            }
        });
        proxyCarDriver.drive();
    }

    @Test
    void testJdkAgent2(){
        Driver carDriver = new CarDriver();
        InvocationHandler carJdkAgent = new CarJdkAgent<Driver>(carDriver);
        Driver proxyDriver= (Driver)Proxy.newProxyInstance(carDriver.getClass().getClassLoader(),
                carDriver.getClass().getInterfaces(), carJdkAgent);
        proxyDriver.drive();
    }
}
