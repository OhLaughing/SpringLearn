package com.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final Pattern ERRORCODE_PATTERN = Pattern.compile("ERRCODE=(\\d+)");

    /**
     * 检查telnet是否返回一个正确的结果
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
            r=  errorCode != null && "0".equals(errorCode);
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
}
