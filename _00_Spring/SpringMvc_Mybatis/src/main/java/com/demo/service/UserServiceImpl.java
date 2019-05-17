package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;
    @Override
    public List<User> getUserService(int id) {
        List<User> username = userdao.getUser(id);
        return username;
    }

}
