package com.demo;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final Pattern ERRORCODE_PATTERN = Pattern.compile("ERRCODE=(\\d+)");
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 检查telnet是否返回一个正确的结果
     * 正确的结果返回true，错误的结果，返回false
     *
     * @param result
     * @return
     */
    public static boolean checkErrorCode(String result) {
        boolean r = true;
        if (result == null) {
            r = false;
        } else if (!result.contains("ERRCODE=")) {
            r = false;
        } else {
            String errorCode = getErrorCode(result);
            r = "0".equals(errorCode);
        }
        return r;
    }

    public static String getErrorCode(String result) {
        Matcher m = ERRORCODE_PATTERN.matcher(result);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static Map<String, String> getMapInfo(String result) {
        Map<String, String> mapInfo = new HashMap<>();
        if (!Utils.checkErrorCode(result)) {
            return mapInfo;
        }
        result = StringUtils.substringAfter(result, ":");
        if (result.contains(";")) {
            result = StringUtils.substringBefore(result, ";");
        }
        String[] infos = StringUtils.split(result, ",");
        for (String info : infos) {
            if (!info.contains("=")) {
                continue;
            }
            String key = StringUtils.substringBefore(info, "=");
            String value = StringUtils.substringAfter(info, "=");
            if (value.startsWith("\"")) {
                value = StringUtils.substringAfter(value, "\"");
            }
            if (value.endsWith("\"")) {
                value = StringUtils.substringBefore(value, "\"");
            }
            mapInfo.put(key, value);
        }
        return mapInfo;
    }

    public static int getPercent(String progress) {
        if (progress.endsWith("%")) {
            progress = StringUtils.substringBefore(progress, "%");
        }
        int i = 0;
        try {
            i = Integer.valueOf(progress);
        } catch (Exception e) {

        }
        return i;
    }
}
