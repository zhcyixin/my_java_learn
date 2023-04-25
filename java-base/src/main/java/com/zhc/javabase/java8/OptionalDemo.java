package com.zhc.javabase.java8;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Optional使用示例
 * @author zhouhengchao
 * @date 2022-12-21 10:14:00
 * @version 1.0
 */
@Service
public class OptionalDemo {

    public void OptionalUse(){
        Optional<Integer> op = Optional.empty();
        System.out.println("空对象为:"+op);
    }
}
