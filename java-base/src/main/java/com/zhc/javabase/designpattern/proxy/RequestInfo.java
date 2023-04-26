package com.zhc.javabase.designpattern.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestInfo {

    private String operate;

    private long startTimestamp;

    private long responseTime;
}
