package com.zhc.javabase.juc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class JUCTest {

    @Resource
    private CountDownLatchDemo countDownLatchDemo;

    @Resource
    private BlockQueueDemo blockQueueDemo;

    @Test
    void testCountDownLatch() throws InterruptedException {
        countDownLatchDemo.goOutDoor();
    }

    @Test
    void testSynchronousQueue(){
        BlockingQueue<String> blockingQueue = new SynchronousQueue<String>();
        new Thread(() -> {
            try {
                blockingQueue.put("a");
                blockingQueue.put("b");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("取出数据:"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println("取出数据:"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println("取出数据:"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while(true){

        }
    }
}
