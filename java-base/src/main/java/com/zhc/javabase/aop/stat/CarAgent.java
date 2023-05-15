package com.zhc.javabase.aop.stat;

import com.zhc.javabase.aop.CarDriver;
import com.zhc.javabase.aop.Driver;

public class CarAgent implements Driver {

    @Override
    public void drive(){
        System.out.println("开车前要记得加油。");
        CarDriver carDriver = new CarDriver();
        carDriver.drive();
        System.out.println("开车后记得洗车和保养");
    }
}
