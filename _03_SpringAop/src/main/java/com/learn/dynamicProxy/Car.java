package com.learn.dynamicProxy;

/**
 * Created by gustaov on 2019/3/10.
 */
public class Car implements IVehical {
    @Override
    public void run() {
        System.out.println("car is running");
    }
}
