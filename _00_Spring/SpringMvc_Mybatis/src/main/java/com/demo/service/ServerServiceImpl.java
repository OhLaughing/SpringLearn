package com.demo.service;

import com.demo.CheckException;
import com.demo.MmlException;
import com.demo.Utils;
import com.demo.entity.Server;
import com.demo.mapper.ServerMapper;
import com.demo.telnet.ShowDataVersionMml;
import com.demo.telnet.ShowServerVersionMml;
import com.demo.telnet.Telnet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerMapper serverMapper;

    @Override
    public int add(Server server) throws MmlException, CheckException {
        connectServer(server);
        server.setActive(true);
        server.setUpdateTime(Utils.sdf.format(new Date()));
        return serverMapper.add(server);
    }

    private void connectServer(Server server) throws CheckException, MmlException {
        Telnet telnet = new Telnet(server);
        Map<String, String> result = telnet.sendCmd(new ShowDataVersionMml());
        String code = result.get("ERRCODE");

        if (Integer.valueOf(code) == 0) {
            server.setDataVersion(result.get("VERSION"));
        } else {
            throw new CheckException(1, "connect serverFailure");
        }
        result = telnet.sendCmd(new ShowServerVersionMml());
        code = result.get("ERRCODE");

        if (Integer.valueOf(code) == 0) {
            server.setServerVersion(result.get("VERSION"));
        } else {
            throw new CheckException(1, "connect serverFailure");
        }
    }

    //TODO delete 是否要返回原数据
    @Override
    public int delete(int id) {
        return serverMapper.delete(id);

    }

    @Override
    public int update(Server server) {
        try {
            connectServer(server);
            server.setActive(true);
            server.setUpdateTime(Utils.sdf.format(new Date()));
            return serverMapper.update(server);
        } catch (CheckException e) {
            e.printStackTrace();
        } catch (MmlException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Server find(int id) {
        return serverMapper.find(id);
    }
}
