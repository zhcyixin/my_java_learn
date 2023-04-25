package com.zhc.javabase.designpattern.ocp;

public class ErrorAlertHandler extends AlertHandler {
    public ErrorAlertHandler(AlertRule rule, Notification notification){
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo){
        System.out.println("处理错误告警");
    }
}
