package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gustaov on 2019/4/13.
 */
@Configuration
public class MyConfig {
    @Bean
    @ConditionalOnBean(B.class)
    public A a() {
        return new A();
    }

    @Bean
    public C c() {
        return new C();
    }
}
