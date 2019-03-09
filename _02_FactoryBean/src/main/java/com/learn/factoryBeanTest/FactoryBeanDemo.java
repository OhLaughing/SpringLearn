package com.learn.factoryBeanTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by gustaov on 2019/3/9.
 */
public class FactoryBeanDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("factoryBean.xml");

        NextDayDateDisplayer nextDayDateDisplayer = (NextDayDateDisplayer) context.getBean("nextDayDateDisplayer");
        System.out.println(nextDayDateDisplayer.getDateOfNextDay());


    }
}
