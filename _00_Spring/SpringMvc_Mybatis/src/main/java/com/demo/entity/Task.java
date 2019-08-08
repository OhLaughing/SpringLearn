package com.demo.entity;

import lombok.Data;

@Data
public class Task {
    private int id;
    private String taskNo;
    private String taskName;
    private String createTime;
    private String startTime;
    private String endTime;
    private String sourceVersion;
    private String targetVersion;
    private String filePath;
    private int progess;
    private int serverId;
    //0 -created 1- executing 2- finished
    private int status;
}
