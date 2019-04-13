package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by gustaov on 2019/4/13.
 */
@Configuration
@PropertySource("classpath:/config/user.properties")
public class UserConfig {
    @Autowired
    Environment env;

    @Bean
    public User user(){
        return new User(env.getRequiredProperty("u.name"), Integer.valueOf(env.getProperty("u.age")));
    }
}
