package com.demo.mapper;

import com.demo.entity.User;

public interface UserMapper {
    User find(int id);
    User add(User user);
    int update(User user);
    int delete(int id);
}
