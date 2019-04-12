package com.example.demo;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by gustaov on 2019/4/12.
 */
public class User {
    @Value("${u.name}")
    private String userName;

    @Value("${u.age}")
    private Integer age;
}
