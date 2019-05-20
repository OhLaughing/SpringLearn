package com.demo.service;

import com.demo.dao.ServerDao;
import com.demo.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerDao serverDao;

    @Override
    public Server add(Server server) {
        int i = serverDao.add(server);
        if (i > 0)
            return server;
        else return null;
    }

    @Override
    public Server delete(int id) {
        return serverDao.delete(id);
    }

    @Override
    public int update(Server server) {
        return serverDao.update(server);
    }

    @Override
    public Server find(int id) {
        return serverDao.find(id);
    }
}
