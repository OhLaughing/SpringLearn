package com.demo.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by gustaov on 2019/4/14.
 */
@Configuration
public class Config {
    @Bean
    @Profile("dev")
    public DemoBean demoBean1(){
        return new DemoBean("Demo bean from dev");
    }

    @Bean
    @Profile("prod")
    public DemoBean demoBean2(){
        return new DemoBean("Demo bean from prod");
    }
}
