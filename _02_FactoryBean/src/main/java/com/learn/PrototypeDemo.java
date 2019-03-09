package com.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by gustaov on 2019/3/9.
 */
public class PrototypeDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mybeans.xml");
        Foo f1 = (Foo) context.getBean("foo_1");
        Foo f2 = (Foo) context.getBean("foo_1");
        System.out.println(f1 == f2);
    }
}
