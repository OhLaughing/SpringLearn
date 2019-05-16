package com.example.demo;

/**
 * Created by gustaov on 2019/4/26.
 */
public class PublicClass {
    private static String username;

    public void setUsername(String username) {
        PublicClass.username = username;
    }

    public String getUsername() {
        return username;
    }

    public class InnerClass{

    }
}
