package com.zhc.javabase.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAccumulator;

public class CasDemo {

    private AtomicInteger count = new AtomicInteger(0);

    private int countInt=0;

    public static void main(String[] args) {
        CasDemo casDemo = new CasDemo();
        //casDemo.atomicUse();
        casDemo.testInt();
    }

    public void atomicUse(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(12);
        // 获取当前数值
        System.out.println(atomicInteger.get());
    }

    public void increase(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countInt++;
        count.getAndIncrement();  //自增
    }

    public void testInt(){
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                increase();
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
            System.out.println("计算结果为countInt:"+countInt+",count"+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
