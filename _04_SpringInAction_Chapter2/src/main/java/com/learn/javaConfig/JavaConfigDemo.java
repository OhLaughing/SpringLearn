package com.learn.javaConfig;

import com.learn.CompactDisc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gustaov on 2019/3/10.
 */
public class JavaConfigDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        CompactDisc disc1 = context.getBean(CompactDisc.class);
        CompactDisc disc2 = context.getBean("sgtPeppers", CompactDisc.class);
        System.out.println(disc1);
        System.out.println(disc2);

    }
}
