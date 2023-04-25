package com.zhc.javabase.designpattern.ocp;

public class TpsAlertHandler extends AlertHandler{

    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }
    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount()/ apiStatInfo.getDurationOfSeconds();
        if (tps > rule.hashCode()) {
            System.out.println("处理tps告警");
        }
    }
}
