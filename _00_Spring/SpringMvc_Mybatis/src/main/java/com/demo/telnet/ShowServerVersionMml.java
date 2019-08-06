package com.demo.telnet;

import com.demo.Utils;

import java.util.Map;

public class ShowServerVersionMml implements MmlExecutor {
    private static String SHOW_SERVER_VERSION = "CHECK SERVER VERSION";

    @Override
    public String getMml() {
        return SHOW_SERVER_VERSION;
    }

    @Override
    public Map<String, String> parseResult(String result) {
        return Utils.getMapInfo(result);
    }
}
