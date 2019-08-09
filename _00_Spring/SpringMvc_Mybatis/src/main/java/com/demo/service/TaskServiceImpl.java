package com.demo.service;

import com.demo.*;
import com.demo.entity.Server;
import com.demo.entity.Task;
import com.demo.mapper.ServerMapper;
import com.demo.mapper.TaskMapper;
import com.demo.telnet.CheckUpdateResultMml;
import com.demo.telnet.DataUpdateMml;
import com.demo.telnet.Telnet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by gustaov on 2019/7/31.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ServerMapper serverMapper;

    @Override
    public int add(Task task) {
        Server server = serverMapper.find(task.getServerId());
        try {
            Telnet telnet = new Telnet(server);
            Map<String, String> resultInfo = telnet.sendCmd(new DataUpdateMml(task));

            if (resultInfo.containsKey("TASKNO")) {
                task.setCreateTime(Utils.sdf.format(new Date()));
                task.setStatus(0);
                task.setTaskNo(resultInfo.get("TASKNO"));
                int result = taskMapper.add(task);
                if (result > 0) {
                    ServerInitListener.getThreadPoolExecutor(task.getServerId()).execute(
                            new CheckResultRunnable(task, taskMapper, server));
                }
                telnet.disconnect();
                return result;
            } else {
                telnet.disconnect();
                return 0;
            }
        } catch (CheckException e) {
            e.printStackTrace();
        } catch (MmlException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        return taskMapper.delete(id);
    }

    @Override
    public int update(Task task) {
        Server server = serverMapper.find(task.getServerId());
        try {
            Telnet telnet = new Telnet(server);
            Map<String, String> resultInfo = telnet.sendCmd(new CheckUpdateResultMml(String.valueOf(task.getTaskNo())));
            telnet.disconnect();
            task.setProgess(Utils.getPercent(resultInfo.get("PROGESS")));
            return taskMapper.update(task);
        } catch (CheckException e) {
            e.printStackTrace();
        } catch (MmlException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Task find(int id) {
        return taskMapper.find(id);
    }
}
