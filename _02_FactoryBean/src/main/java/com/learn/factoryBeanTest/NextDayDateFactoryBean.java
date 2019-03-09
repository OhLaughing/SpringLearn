package com.learn.factoryBeanTest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.FactoryBean;

public class NextDayDateFactoryBean implements FactoryBean {

    public Object getObject() throws Exception {
        return new DateTime().plusDays(1);
    }

    public Class getObjectType() {
        return DateTime.class;
    }

    public boolean isSingleton() {
        return false;
    }
}