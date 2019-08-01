package com.demo;

import lombok.Data;

@Data
public class ReturnMsg {
    private boolean result;
    private String msg;
    private Object detail;
}
