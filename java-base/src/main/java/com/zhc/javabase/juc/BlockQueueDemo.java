package com.zhc.javabase.juc;

import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

@Service
public class BlockQueueDemo {

    public static void main(String[] args) {
        synchronousQueue();
    }

    public static void synchronousQueue() {

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
    }
}
