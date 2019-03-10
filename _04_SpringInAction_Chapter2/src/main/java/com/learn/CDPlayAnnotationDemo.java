package com.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gustaov on 2019/3/10.
 */
public class CDPlayAnnotationDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CDPlayerConfig.class);
        CompactDisc disc = context.getBean(CompactDisc.class);
        disc.play();
    }
}
