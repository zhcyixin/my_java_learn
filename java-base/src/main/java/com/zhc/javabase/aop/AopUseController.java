package com.zhc.javabase.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("com/zhc/javabase/aop")
public class AopUseController {

    @GetMapping("/firstAop")
    @CustomAnnotation
    public String firstAop(){
        System.out.println("哈哈，aop学习");
        return "hello aop";
    }

}
