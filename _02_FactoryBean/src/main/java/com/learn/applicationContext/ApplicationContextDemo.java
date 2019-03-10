package com.learn.applicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 把applicationContext作为一个bean注入但另一bean中
 */
public class ApplicationContextDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext/beans.xml");
        FooBar instance = (FooBar) context.getBean("mybean");
        instance.test();
    }
}
