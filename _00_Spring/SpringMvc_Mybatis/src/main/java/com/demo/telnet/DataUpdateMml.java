package com.demo.telnet;

import com.demo.MmlException;

import java.util.Map;

/**
 * 升级命令
 * Created by gustaov on 2019/7/31.
 */
public class DataUpdateMml implements MmlExecutor {
    private static String DATA_UPDATE_MML = "";
    @Override
    public String getMml() {
        return DATA_UPDATE_MML;
    }

    @Override
    public Map<String, Object> parseResult(String result) throws MmlException {
        return null;
    }
}
