package com.example.demo1;

import com.example.demo.PublicClass;

/**
 * Created by gustaov on 2019/4/26.
 */
public class Run {
    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();
        publicClass.setUsername("hello");
        PublicClass p1  = new PublicClass();
        System.out.println(p1.getUsername());
    }
}
