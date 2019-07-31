package com.demo.controller;

import com.demo.entity.Task;
import com.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gustaov on 2019/7/30.
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(Task task) {
        Task t = taskService.add(task);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(int id) {
        Task t = taskService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(Task task) {
        int result = taskService.update(task);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public void find(int id) {
        Task t = taskService.find(id);
    }
}
