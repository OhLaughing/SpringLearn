package com.demo;

import com.demo.entity.Task;
import com.demo.mapper.TaskMapper;
import com.demo.telnet.CheckUpdateResultMml;
import com.demo.telnet.Telnet;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CheckResultRunnable implements Runnable {
    private Task task;
    private TaskMapper taskMapper;
    private Telnet telnet;

    public CheckResultRunnable(Task task, TaskMapper taskMapper, Telnet telnet) {
        this.task = task;
        this.taskMapper = taskMapper;
        this.telnet = telnet;
    }

    @Override
    public void run() {
        boolean isFinish = false;
        task.setStatus(1);
        task.setStartTime(Utils.sdf.format(new Date()));
        while (!isFinish) {
            try {
                Map<String, String> infos = telnet.sendCmd(new CheckUpdateResultMml(task.getTaskNo()));
                if (infos.containsKey("PROGESS")) {
                    int progress = Utils.getPercent(infos.get("PROGESS"));
                    task.setProgess(progress);
                    log.info("task is executing: " + task);
                    taskMapper.update(task);
                    if (progress == 100) {
                        isFinish = true;
                        task.setEndTime(Utils.sdf.format(new Date()));
                    }
                }
                TimeUnit.SECONDS.sleep(3);
            } catch (MmlException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("task is finished: " + task);
    }
}
