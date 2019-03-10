package com.learn;

/**
 * Created by gustaov on 2019/3/9.
 */
public class InstanceWithSataic {
    static {
        System.out.println("InstanceWithSataic static ..");
    }
    public void test(){
        System.out.println("InstanceWithSataic.test()..");
    }
}
