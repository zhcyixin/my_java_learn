package com.zhc.javabase.java8;

/**
 * Lambda表达式使用
 * @author zhouhengchao
 * @date 2022-11-23 20:13:00
 * @version 1.0
 */
// 1、匿名内部类没有类名，只被使用一次，使代码更简洁；
// 2、在定义之后就可以立即使用，定义时就实例化
// 3、方便编写事件驱动程序及线程代码
public class LambdaUse {
    // 匿名内部类
    public void anonymousInner(String hello){
        HelloFunction helloFunction = new HelloFunction(){
            @Override
            public void sayHello(String hello){
                System.out.println(hello);
            }
        };
        helloFunction.sayHello(hello);
    }
    // 局部类
    public void implementsClass(){
        class Hello implements HelloFunction{
            @Override
            public void sayHello(String hello) {
                System.out.println("扩展类："+hello);
            }
        }
        Hello hello = new Hello();
        hello.sayHello("匿名内部类");
    }
    // lambda表达式
    public void lambda(String hello){
        HelloFunction helloFunction = System.out::println;
        helloFunction.sayHello(hello);
    }

    public static void main(String[] args) {
        LambdaUse lambdaUse = new LambdaUse();
        lambdaUse.lambda("你好匿名内部类。");
        lambdaUse.implementsClass();
    }
    // 匿名内部类：可以是接口，也可以是抽象类，还可以是具体类
    // Lambda表达式：只可能是接口,如果接口中仅有一个抽象方法，可以使用Lambda表达式，也可以使用匿名表达式，
    // 如果接口中有多个抽象，只能使用匿名内部类
}
