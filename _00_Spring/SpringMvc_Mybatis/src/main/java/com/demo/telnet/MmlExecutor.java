package com.demo.telnet;

import com.demo.MmlException;

import java.util.Map;

public interface MmlExecutor {
    String getMml();

    Map<String, Object> excuteResult(String result) throws MmlException;
}
