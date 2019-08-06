package com.demo.telnet;

import com.demo.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gustaov on 2019/7/31.
 */
public class ShowDataVersionMml implements MmlExecutor {
    private static String SHOW_DATA_VERSION_MML = "CHECK DATA VERSION";

    @Override
    public String getMml() {
        return SHOW_DATA_VERSION_MML;
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
