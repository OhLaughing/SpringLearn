package com.demo.autowire;

import org.springframework.stereotype.Repository;

/**
 * Created by gustaov on 2019/4/14.
 */
@Repository
public class UserRepository {

    public void save(){
        System.out.println("UserRepository save..");
    }
}
