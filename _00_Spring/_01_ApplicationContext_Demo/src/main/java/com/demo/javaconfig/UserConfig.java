package com.demo.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gustaov on 2019/4/14.
 */
@Configuration
public class UserConfig {
    @Bean
    public UserRepository repository() {
        return new UserRepository();
    }

    @Bean
    public UserService service() {
        return new UserService(repository());
    }
}
