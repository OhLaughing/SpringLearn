package com.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by gustaov on 2019/3/10.
 */
public class FileSystemXmlApplicationContextDemo {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("mybeans.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:mybeans.xml");
        Foo f = (Foo) context.getBean("foo");
        f.func();
    }


}
