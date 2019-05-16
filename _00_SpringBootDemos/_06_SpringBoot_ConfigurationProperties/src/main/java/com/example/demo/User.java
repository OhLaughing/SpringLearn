package com.example.demo;

/**
 * Created by gustaov on 2019/4/27.
 */
public class User {
    private String name;
    private String msg;

    public User(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
