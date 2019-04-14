package com.demo.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gustaov on 2019/4/14.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
        User u1 = context.getBean(User.class);
        User u2 = context.getBean(User.class);
        System.out.println(u1 == u2);
    }
}
