package com.demo.entity;

import lombok.Data;

@Data
public class Task {
    private int taskNo;
    private String taskName;
    private String createTime;
    private String startTime;
    private String endTime;
    private String sourceVersion;
    private String targetVersion;
    private String filePath;
    private int progress;
    private int serverId;
    private int status;
}
