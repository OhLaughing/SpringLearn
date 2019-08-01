package com.demo.telnet;

import com.demo.MmlException;

import java.util.Map;

public class ShowServerVersionMml implements MmlExecutor {
    private static String SHOW_SERVER_VERSION = "SHOW SERVERVERSION";

    @Override
    public String getMml() {
        return SHOW_SERVER_VERSION;
    }

    @Override
    public Map<String, Object> parseResult(String result) throws MmlException {
        return null;
    }
}
