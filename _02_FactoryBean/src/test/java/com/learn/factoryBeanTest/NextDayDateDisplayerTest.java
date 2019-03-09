package com.learn.factoryBeanTest;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by gustaov on 2019/3/9.
 */
public class NextDayDateDisplayerTest {
    @Test
    public void test() throws Exception {
        ApplicationContext container = new ClassPathXmlApplicationContext("factoryBean.xml");
        Object nextDayDate = container.getBean("nextDayDate");
        assertTrue(nextDayDate instanceof DateTime);

        Object factoryBean = container.getBean("&nextDayDate");
        assertTrue(factoryBean instanceof FactoryBean);
        assertTrue(factoryBean instanceof NextDayDateFactoryBean);
        Object factoryValue = ((FactoryBean) factoryBean).getObject();
        assertTrue(factoryValue instanceof DateTime);

        assertNotSame(nextDayDate, factoryValue);
        assertEquals(((DateTime) nextDayDate).getDayOfYear(), ((DateTime) factoryValue).getDayOfYear());
    }
}