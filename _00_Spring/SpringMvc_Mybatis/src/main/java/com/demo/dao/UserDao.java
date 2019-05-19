package com.demo.dao;

import com.demo.entity.User;

import java.util.List;

public interface UserDao {
    User getUser(Integer id);
    User addUser(User user);
    boolean updateUser(User user);
    boolean delete(int id);
}
