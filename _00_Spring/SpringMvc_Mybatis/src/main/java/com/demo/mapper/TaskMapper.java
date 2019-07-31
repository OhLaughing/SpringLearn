package com.demo.mapper;

import com.demo.entity.Task;

/**
 * Created by gustaov on 2019/7/30.
 */
public interface TaskMapper {
    Task add(Task task);
    int delete(int id);
    int update(Task task);
    Task find(int id);
}
