package com.demo.controller;

import com.demo.ReturnMsg;
import com.demo.entity.Server;
import com.demo.entity.Task;
import com.demo.service.ServerService;
import com.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

/**
 * Created by gustaov on 2019/7/30.
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ServerService serverService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg add(@RequestBody Task task) {
        int t = taskService.add(task);
        if (t > 0) {
            return new ReturnMsg(true, "add task successful, taskNo:" + task.getTaskNo(), task);
        } else {
            return new ReturnMsg(false, "add task failure");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg delete(int id) {
        int t = taskService.delete(id);
        if (t > 0) {
            return new ReturnMsg(true, "delete task successful");
        } else {
            return new ReturnMsg(false, "delete task failure");
        }
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Task find(@PathVariable("id") int id) {
        System.out.println(id);
        Task task = taskService.find(id);
        System.out.println(task);
        return task;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg update(@PathVariable("id") int id) {
        Task t = taskService.find(id);
        if (t == null) {
            return new ReturnMsg(false, "task not found: taskNo: " + id);
        }
        int result = taskService.update(t);
        if (result > 0) {
            return new ReturnMsg(true, "update task success", t);
        } else {
            return new ReturnMsg(false, MessageFormat.format("update task: {0} failure", t.getTaskNo()));
        }
    }
}
