package com.demo.entity;

import lombok.Data;

@Data
public class Task {
    private String createTime;
    private String endTime;
    private String filePath;
    private Server server;
    private int serverId;
    private String sourceVersion;
    private String startTime;
    private int status;
    private String targetVersion;
    private String taskName;
    private int taskNo;

}
