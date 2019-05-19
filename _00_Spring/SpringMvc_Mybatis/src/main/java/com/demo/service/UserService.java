package com.demo.service;

import com.demo.entity.User;

import java.util.List;

public interface UserService {
    User getUser(int id);
    User addUser(User user);
    boolean updateUser(User user);
    boolean delete(int id);
}
