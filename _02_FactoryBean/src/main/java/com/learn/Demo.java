package com.learn;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * BeanFactory和AppliactionContext的区别，BeanFactory在加载xml配置文件时，采用延迟初始化
 * 而AppliactionContext，容器启动就实例化bean
 */
public class Demo {
    public static void main(String[] args) {
        BeanFactory context = new XmlBeanFactory(new FileSystemResource("D:\\MYWORKSPACE\\springLearn\\SpringLearn\\_02_FactoryBean\\src\\main\\resources\\mybeans.xml"));
        Foo f = (Foo) context.getBean("foo");
        f.func();

        DateFoo dateFoo = (DateFoo) context.getBean("dateFoo");
        System.out.println(dateFoo.getDate());


    }
}
