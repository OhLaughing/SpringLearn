package com.demo.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gustaov on 2019/4/14.
 */
@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public void save(){
        repository.save();
    }

}
