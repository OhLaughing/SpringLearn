package com.learn;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * BeanFactory和AppliactionContext的区别，BeanFactory在加载xml配置文件时，采用延迟初始化
 * 而AppliactionContext，容器启动就实例化bean
 */
public class DiffOfBeanFactory_AppliactionContext {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mybeans.xml");
//        BeanFactory context = new XmlBeanFactory(new ClassPathResource("mybeans.xml"));
        Foo f = (Foo) context.getBean("foo");
        f.func();

        DateFoo dateFoo = (DateFoo) context.getBean("dateFoo");
        System.out.println(dateFoo.getDate());


    }
}
