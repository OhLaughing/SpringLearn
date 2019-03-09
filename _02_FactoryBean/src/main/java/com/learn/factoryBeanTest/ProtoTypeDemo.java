package com.learn.factoryBeanTest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 只有当MockNewsPersister和newsBean的bean描述都有scope="prototype"时，取出的MockNewsPersiste里的newsBean
 * 才不是同一个，否则，取出的newsBean都是同一个
 */
public class ProtoTypeDemo {
    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("factoryBean.xml");
        MockNewsPersister persister1 = (MockNewsPersister) factory.getBean("mockPersister");
        MockNewsPersister persister2 = (MockNewsPersister) factory.getBean("mockPersister");

        persister1.persistNews();
        persister2.persistNews();
    }
}
