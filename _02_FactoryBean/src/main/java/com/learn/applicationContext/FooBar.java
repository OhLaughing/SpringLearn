package com.learn.applicationContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ResourceLoader;

/**
 * Created by gustaov on 2019/3/10.
 */
public class FooBar implements ApplicationContextAware {
    private ResourceLoader resourceLoader;

    public void foo(String location) {
        System.out.println(getResourceLoader().getResource(location).getClass());
    }

    public void test() {
        System.out.println(resourceLoader);
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.resourceLoader = ctx;
    }
}