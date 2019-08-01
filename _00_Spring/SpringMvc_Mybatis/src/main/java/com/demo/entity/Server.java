package com.demo.entity;

import lombok.Data;

@Data
public class Server {
    private int id;
    private String desc;
    private String ip;
    private String port;
    private String version;
    private String dataVersion;
    private String username;
    private String password;
}
