package com.demo.dao;

import com.demo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getUser(Integer id);
}
