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

    public void optionalGet(){
        Optional<String> optional = Optional.of("Hello optional");
        System.out.println("optional get is :"+optional.get());
        optional.ifPresent(System.out::println);
        String aa = optional.orElse("haha");
        System.out.println(aa);
        try {
            String bb = optional.orElseThrow(() -> new Exception("抛出异常"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
