package com.learn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by gustaov on 2019/3/10.
 */
@Configuration
public class Config {
    @Bean
    @Profile("disc1")
    public CompactDisc disc1() {
        return new BlankDisc("Sgt. Pepper's", "The Beatles");
    }

    @Bean
    @Profile("disc2")
    public CompactDisc disc2() {
        return new BlankDisc("Hey Jude", "The Beatles");
    }
}
