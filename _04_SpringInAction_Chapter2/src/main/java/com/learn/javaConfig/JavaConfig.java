package com.learn.javaConfig;

import com.learn.CompactDisc;
import com.learn.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gustaov on 2019/3/10.
 */
@Configuration
public class JavaConfig {
    @Bean
    public CompactDisc disc() {
        return new SgtPeppers();
    }
}
