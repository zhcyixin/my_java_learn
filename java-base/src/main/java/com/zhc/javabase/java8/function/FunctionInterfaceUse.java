package com.zhc.javabase.java8.function;

import java.util.function.Consumer;

/**
 * 常见函数式接口使用
 * @author zhouhengchao
 * @date 2023-04-26 17:14:00
 * @version 1.0
 */
public class FunctionInterfaceUse {

    public static void consumerAccept1(){
        handleConsumer(123, s-> System.out.printf("consumerAccept--> {%s}%n", (s + s)));
    }

    public static void consumerAccept2(){
        handleConsumer(123, new Consumer<Integer>() {
            @Override
            public void accept(Integer s) {
                System.out.printf("consumerAccept--> {%s}%n", (s + s + 1));
            }
        });
    }

    private static <T> void handleConsumer(T t, Consumer<T> consumer){
        consumer.accept(t);
    }

    public static void main(String[] args) {
        FunctionInterfaceUse.consumerAccept1();
        FunctionInterfaceUse.consumerAccept2();
    }
}
