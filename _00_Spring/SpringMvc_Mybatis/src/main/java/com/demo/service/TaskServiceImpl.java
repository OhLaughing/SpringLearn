package com.demo.service;

import com.demo.entity.Task;
import com.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gustaov on 2019/7/31.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Task add(Task task) {
        return taskMapper.add(task);
    }

    @Override
    public Task delete(int id) {
        Task t = taskMapper.find(id);
        int r = taskMapper.delete(id);
        return r > 0 ? t : null;
    }

    @Override
    public int update(Task task) {
        return taskMapper.update(task);
    }

    @Override
    public Task find(int id) {
        return taskMapper.find(id);
    }
}
