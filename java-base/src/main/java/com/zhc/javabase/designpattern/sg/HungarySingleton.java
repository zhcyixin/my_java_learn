package com.zhc.javabase.designpattern.sg;

/**
 * 饿汉式-单例
 */
public class HungarySingleton {

    private HungarySingleton (){}

    private final static HungarySingleton instance = new HungarySingleton();

    public static HungarySingleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        HungarySingleton instance1 = HungarySingleton.getInstance();
        HungarySingleton instance2 = HungarySingleton.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println("instance1.hashCode = " + instance1.hashCode());
        System.out.println("instance2.hashCode = " + instance2.hashCode());
    }
}
