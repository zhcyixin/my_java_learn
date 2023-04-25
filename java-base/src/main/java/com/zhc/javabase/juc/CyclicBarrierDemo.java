package com.zhc.javabase.juc;

import org.springframework.stereotype.Service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 线程累加器
 * @author zhouhengchao
 * @date 2022-12-21 14:54:00
 * @version 1.0
 */
@Service
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("集齐7科龙珠，召唤神龙成功。");
        });

        for(int i = 0; i < 7; i++){
            int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+ temp +"个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
