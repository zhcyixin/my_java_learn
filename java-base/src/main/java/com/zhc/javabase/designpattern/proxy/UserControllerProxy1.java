package com.zhc.javabase.designpattern.proxy;

/**
 * 基于继承的方式
 * 实现代理
 * @author zhouhengchao
 * @date 2023-04-26 15:43:00
 * @version 1.0
 */
public class UserControllerProxy1 extends UserController {
    private MetricsCollector metricsCollector;

    public UserControllerProxy1() {
        this.metricsCollector = new MetricsCollector();
    }

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        UserVo userVo = super.login(telephone, password);

        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        UserVo userVo = super.register(telephone, password);

        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }

    public static void main(String[] args) {

        //UserControllerProxy使用举例
        UserController userController = new UserControllerProxy1();
    }
}


