package com.demo.entity;

import lombok.Data;

@Data
public class Task {
    private int taskNo;
    private String taskName;
    private int status;
    private String createTime;
    private String startTime;
    private String endTime;
    private int serverId;

    private Server server;

}
