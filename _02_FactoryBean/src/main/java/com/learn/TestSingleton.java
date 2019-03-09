package com.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by gustaov on 2019/3/9.
 */
public class TestSingleton {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mybeans.xml");
        Foo f1 = (Foo) context.getBean("foo");
        Foo f2 = (Foo) context.getBean("foo");
        System.out.println(f1 == f2);
    }
}
