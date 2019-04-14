package com.demo.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by gustaov on 2019/4/14.
 */
@Configuration
public class UserConfig {
    @Bean
    @Scope("prototype")
    public User user(){
        return new User();
    }
}
