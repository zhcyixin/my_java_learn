package com.zhc.javabase.java8.function;

/**
 * @author zhouhengchao
 * @date 2022-11-23 20:51:00
 * @version 1.0
 */
@FunctionalInterface
public interface HelloFunction {

    /**
     * hello函数
     * @param hello
     */
     void sayHello(String hello);
}
