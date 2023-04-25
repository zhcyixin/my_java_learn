package com.zhc.javabase.designpattern.sg;

/**
 * 懒汉式
 */
public class LazySinglelton {

    private LazySinglelton(){

    }

    private final static LazySinglelton instance  = null;

    public static LazySinglelton getInstance(){
        if(instance == null){
            synchronized (LazySinglelton.class){
                if(instance == null){
                    return new LazySinglelton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        LazySinglelton instance1 = LazySinglelton.getInstance();
        LazySinglelton instance2 = LazySinglelton.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println("instance1.hashCode = " + instance1.hashCode());
        System.out.println("instance2.hashCode = " + instance2.hashCode());

    }
}
