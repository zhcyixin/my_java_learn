package com.zhc.javabase.juc;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * 并发工具包使用,
 * 示例：人都出门后才关门
 * @author zhouhengchao
 * @date 2022-12-21 13:44:00
 * @version 1.0
 */
@Service
public class CountDownLatchDemo {

    public void goOutDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i = 0; i < 6; i++){
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName()+"go out door");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("可以关门了");
    }

}
