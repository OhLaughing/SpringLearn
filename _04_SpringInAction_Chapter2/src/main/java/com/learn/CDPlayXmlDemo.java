package com.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by gustaov on 2019/3/10.
 */
public class CDPlayXmlDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CompactDisc disc = context.getBean("beatlesSong",CompactDisc.class);
        disc.play();
    }
}
