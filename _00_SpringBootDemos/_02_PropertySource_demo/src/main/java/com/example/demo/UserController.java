package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gustaov on 2019/4/13.
 */
@RestController
public class UserController {
    @Autowired
    User user;

    @RequestMapping("user")
    public String user(){
        return user.toString();
    }


    @RequestMapping("user1")
    public User user1(){
        return user;
    }
}
