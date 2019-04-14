package com.demo.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gustaov on 2019/4/14.
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(Config.class);
        context.refresh();
        DemoBean bean = context.getBean(DemoBean.class);
        System.out.println(bean.getInfo());
    }
}
