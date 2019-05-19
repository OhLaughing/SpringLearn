package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;

    @Override
    public User getUser(int id) {
        User username = userdao.getUser(id);
        return username;
    }

    @Override
    public User addUser(User user) {
        return userdao.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userdao.updateUser(user);
    }

    @Override
    public boolean delete(int id) {
        return userdao.delete(id);
    }
}
