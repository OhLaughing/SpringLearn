package com.demo.telnet;

import com.demo.MmlException;

import java.util.Map;

/**
 *
 * Created by gustaov on 2019/7/31.
 */
public class ShowDataVersionMml implements MmlExecutor {
    private static String SHOW_DATA_VERSION_MML = "";
    @Override
    public String getMml() {
        return SHOW_DATA_VERSION_MML;
    }

    @Override
    public Map<String, Object> parseResult(String result) throws MmlException {
        return null;
    }
}
