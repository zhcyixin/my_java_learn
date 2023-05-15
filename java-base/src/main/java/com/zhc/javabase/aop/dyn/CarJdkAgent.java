package com.zhc.javabase.aop.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CarJdkAgent<T> implements InvocationHandler {

    private T target;
    public CarJdkAgent(T target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        System.out.println("调用方法前");
        Object ret = method.invoke(target,args);
        return ret;
    }
}
