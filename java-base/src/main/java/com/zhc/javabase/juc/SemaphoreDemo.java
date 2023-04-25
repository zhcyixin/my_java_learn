package com.zhc.javabase.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量-限流多线程使用
 * 抢车位为例
 * @author zhouhengchao
 * @date 2022-12-21 18:23:00
 * @version 1.0
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0; i < 8; i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }


}
