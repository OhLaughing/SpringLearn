package com.demo.dao;

import com.demo.entity.Server;

public interface ServerDao {
    int add(Server server);
    int delete(int id);
    int update(Server server);
    Server find(int id);
}
