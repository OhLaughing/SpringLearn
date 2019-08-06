package com.demo.telnet;

import com.demo.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ShowServerVersionMml implements MmlExecutor {
    private static String SHOW_SERVER_VERSION = "CHECK SERVER VERSION";

    @Override
    public String getMml() {
        return SHOW_SERVER_VERSION;
    }

    @Override
    public Map<String, String> parseResult(String result) {
        Map<String, String> map = new HashMap<>();
        if (!Utils.checkErrorCode(result)) {
            return map;
        }
        result = StringUtils.substringAfter(result, ":");
        if(result.endsWith(";")){
            result = StringUtils.substringBefore(result, ";");
        }
        return Utils.getMapInfo(result);
    }
}
