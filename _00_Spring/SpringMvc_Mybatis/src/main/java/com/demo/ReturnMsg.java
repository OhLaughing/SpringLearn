package com.demo;

import lombok.Data;

@Data
public class ReturnMsg {
    private boolean result;
    private String msg;
    private Object detail;

    public ReturnMsg(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public ReturnMsg(boolean result, String msg, Object detail) {
        this.result = result;
        this.msg = msg;
        this.detail = detail;
    }
}
