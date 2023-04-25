package com.zhc.javabase.reflect;

import com.zhc.javabase.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ReflectTest {

    @Test
    void testGetClass() throws ClassNotFoundException {
        log.info("根据类名获取:{}", UserInfo.class);
        log.info("根据对象获取:{}",new UserInfo().getClass());
        Class userInfo = Class.forName("com.zhc.javabase.entity.UserInfo");
        log.info("根据类全路径获取:{}",userInfo);
        // 反射中API方法
        log.info("获取全限定类名:{}",userInfo.getName());
        log.info("获取类名:{}",userInfo.getSimpleName());
        log.info("该类是否为接口:{}",userInfo.isInterface());
        log.info("引用的类实现的所有接口:{}",userInfo.getInterfaces());
        log.info("获取类的基类:{}",userInfo.getSuperclass());
        try {
            log.info("实例化类:{}",userInfo.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
