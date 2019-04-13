package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoApplication {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("my-beans.xml");
//        Car c = context.getBean("p", Car.class);
//        c.run();

        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Car c = context.getBean("p", Car.class);
        c.run();
    }

}
