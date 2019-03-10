package com.learn.dynamicProxy;

/**
 * Created by gustaov on 2019/3/10.
 */
public class Main {
    public static void main(String[] args) {
        IVehical car = new Car();
        VehicalProxy proxy = new VehicalProxy(car);

        IVehical proxyObj = proxy.create();
        proxyObj.run();
    }
}
