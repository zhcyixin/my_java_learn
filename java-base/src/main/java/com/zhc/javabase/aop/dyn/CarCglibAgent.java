package com.zhc.javabase.aop.dyn;

import com.zhc.javabase.aop.CarDriver;
import com.zhc.javabase.aop.Driver;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 * @author zhouhengchao
 * @date 2023-05-15 19:50:00
 * @version 1.0
 */
public class CarCglibAgent implements MethodInterceptor {

    public Object getProxy(Class cls) {
        // CGLIB <u>enhancer</u>增强类对象
        Enhancer enhancer = new Enhancer();
        // 设置增强类型
        enhancer.setSuperclass(cls);
        // 定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor方法
        enhancer.setCallback(this);
        // 生成并返回代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("开车前先加油");
        // 执行目标对象的方法
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("开完车记得洗车");
        return result;
    }

    public static void main(String[] args) {
        CarCglibAgent carCglibAgent = new CarCglibAgent();
        CarDriver carDriver = (CarDriver)carCglibAgent.getProxy(CarDriver.class);
        carDriver.drive();
    }

}
