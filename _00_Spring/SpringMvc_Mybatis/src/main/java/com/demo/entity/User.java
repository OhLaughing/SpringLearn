package com.demo.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private Integer age;

    public Integer getId() {
        return id;
    }

}
