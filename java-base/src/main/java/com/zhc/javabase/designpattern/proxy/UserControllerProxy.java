package com.zhc.javabase.designpattern.proxy;

/**
 * 静态代理类
 * @author zhouhengchao
 * @date 2023-04-26 15:34:00
 * @version 1.0
 */
public class UserControllerProxy implements IUserController{

    private MetricsCollector metricsCollector;
    private UserController userController;

    public UserControllerProxy(UserController userController) {
        this.userController = userController;
        this.metricsCollector = new MetricsCollector();
    }

    @Override public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        // 委托 UserController
        UserVo userVo = userController.login(telephone, password);
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo); return userVo;
    }
    @Override public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = userController.register(telephone, password);
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);
        return userVo;
    }

    public static void main(String[] args) {
        UserControllerProxy userControllerProxy = new UserControllerProxy(new UserController());
        userControllerProxy.login("aa","123456");
    }
}
