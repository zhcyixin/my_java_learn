package com.zhc.javabase.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) {
        FutureTaskDemo futureTaskDemo = new FutureTaskDemo();
        //futureTaskDemo.testFutureTask1();
        futureTaskDemo.testFutureTask2();
    }

    public void testFutureTask1(){
        FutureTask futureTask = new FutureTask(() ->{
            return 1024;
        });
        new Thread(futureTask).start();
        try {
            System.out.println("线程执行结果为:"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void testFutureTask2(){
        FutureTask futureTask = new FutureTask(()->{
            System.out.println("线程执行中:");
        },"success");
        new Thread(futureTask).start();
        try {
            futureTask.run();
            System.out.println("线程执行结果为:"+futureTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
