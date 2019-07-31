package com.demo.service;

import com.demo.mapper.ServerMapper;
import com.demo.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerMapper serverMapper;

    @Override
    public Server add(Server server) {
        int i = serverMapper.add(server);
        if (i > 0)
            return server;
        else return null;
    }

    //TODO delete 是否要返回原数据
    @Override
    public Server delete(int id) {
        Server s = serverMapper.find(id);
        int i = serverMapper.delete(id);
        return i > 0 ? s : null;

    }

    @Override
    public int update(Server server) {
        return serverMapper.update(server);
    }

    @Override
    public Server find(int id) {
        return serverMapper.find(id);
    }
}
