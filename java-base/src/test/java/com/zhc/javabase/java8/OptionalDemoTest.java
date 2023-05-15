package com.zhc.javabase.java8;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OptionalDemoTest {

    @Resource
    private OptionalDemo optionalDemo;

    private static List<UserInfo> userInfoList = new ArrayList<UserInfo>();

    @BeforeAll
    public static void init(){
        userInfoList.add(UserInfo.builder().age(18).idCard(123456).name("小红").memo("备注").build());
        userInfoList.add(UserInfo.builder().age(16).idCard(13456).name("小军").memo("备注1").build());
        userInfoList.add(UserInfo.builder().age(20).idCard(123456).name("小花").memo("备注2").build());
        userInfoList.add(UserInfo.builder().age(18).idCard(123456).name("小容").memo("备注3").build());
        userInfoList.add(UserInfo.builder().age(26).idCard(12346).name("小海").memo("备注4").build());
    }


    @Test
    void testOptionalUse(){
        optionalDemo.OptionalUse();
    }

    @Test
    void testOptionalNull(){
        UserInfo userInfo = userInfoList.get(0);
        Optional.ofNullable(userInfo).map(UserInfo::getAge).ifPresent(item -> System.out.println(item + 2));
        Optional.ofNullable(userInfoList).ifPresent(item -> {
            item.forEach(System.out::println);
        });
    }

    @Test
    void testOptionalFilter(){
        Optional.ofNullable(userInfoList).filter(item -> item.stream().allMatch(o -> o.getAge() > 20)).ifPresent(o -> {
            o.forEach(System.out::println);
        });
    }

    @Data
    @Builder
    static class UserInfo{
        private Integer age;
        private Integer idCard;
        private String name;
        private String memo;

    }
    @Test
    void testOptionalGet(){
        optionalDemo.optionalGet();
    }
}
