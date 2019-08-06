package com.demo.service;

import com.demo.entity.Task;

/**
 * Created by gustaov on 2019/7/30.
 */
public interface TaskService {
    int add(Task task);
    int delete(int id);
    int update(Task task);
    Task find(int id);
}
