package com.demo.telnet;

import java.text.MessageFormat;
import java.util.Map;

/**
 * CHECK UPDATE RESULT:TASKNO=1
 * 返回Ack CHECK UPDATE RESULT:STATUS=3,PROGRESS="22%",DESC="Importting file: 6/14",
 * DETAIL="",RUNTIME=4,ERRCODE=0,ERRMSG="SUCCESS",ERRTEXT="SUCCESS";
 * Created by gustaov on 2019/7/31.
 */
public class CheckUpdateResultMml implements MmlExecutor {
    private static String SHOW_UPDATE_RESULT_MML = "CHECK UPDATE RESULT:TASKNO={0}";
    private String telnetCommand;

    public CheckUpdateResultMml(String taskId) {
        this.telnetCommand = MessageFormat.format(SHOW_UPDATE_RESULT_MML, taskId);
    }

    @Override
    public String getMml() {
        return SHOW_UPDATE_RESULT_MML;
    }

    @Override
    public Map<String, String> parseResult(String result){
        return null;
    }
}
