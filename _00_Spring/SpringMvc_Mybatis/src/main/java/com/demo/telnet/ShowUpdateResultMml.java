package com.demo.telnet;

import com.demo.MmlException;

import java.util.Map;

/**
 * 检查升级结果命令
 * Created by gustaov on 2019/7/31.
 */
public class ShowUpdateResultMml implements MmlExecutor {
    private static String SHOW_UPDATE_RESULT_MML = "CHECK UPDATE RESULT";

    @Override
    public String getMml() {
        return SHOW_UPDATE_RESULT_MML;
    }

    @Override
    public Map<String, String> parseResult(String result) throws MmlException {
        return null;
    }
}
