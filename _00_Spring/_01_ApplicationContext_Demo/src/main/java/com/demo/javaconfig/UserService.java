package com.demo.javaconfig;

/**
 * Created by gustaov on 2019/4/14.
 */
public class UserService {
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void save() {
        repository.save();
    }

}
