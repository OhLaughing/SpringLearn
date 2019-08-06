package com.demo.service;

import com.demo.CheckException;
import com.demo.MmlException;
import com.demo.entity.Server;

public interface ServerService {
    int add(Server server)throws MmlException, CheckException;
    int delete(int id);
    int update(Server server);
    Server find(int id);
}
