package com.example;

import com.example1.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by gustaov on 2019/4/13.
 */
@EnableAutoConfiguration
@ComponentScan
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class,args);
        Hello h = context.getBean(Hello.class);
        h.hello();
    }
}
