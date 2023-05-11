package com.zhc.javabase.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("com/zhc/javabase/aop1")
public class AopUseController1 {

    @GetMapping("/firstAop")
    public String firstAop(){
        System.out.println("哈哈，aop学习");
        return "hello aop";
    }

}
