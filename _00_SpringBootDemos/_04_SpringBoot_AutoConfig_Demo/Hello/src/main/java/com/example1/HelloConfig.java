package com.example1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gustaov on 2019/4/13.
 */
@Configuration
public class HelloConfig {
    @Bean
    public Hello hello(){
        return new Hello();
    }
}
