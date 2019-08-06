package com.demo.telnet;

import com.demo.Utils;
import com.demo.entity.Task;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Map;

/**
 * 升级命令
 * DATA UPDATE:PATH="a",SOURCEVERSION="V1",TARGETVERSION="v2"
 * Created by gustaov on 2019/7/31.
 */
@Slf4j
public class DataUpdateMml implements MmlExecutor {
    private static String DATA_UPDATE_MML = "DATA UPDATE:PATH=\"{0}\",SOURCEVERSION=\"{1}\",TARGETVERSION=\"{2}\"";
    private Task task;

    public DataUpdateMml(Task task) {
        this.task = task;
    }

    @Override
    public String getMml() {
        return MessageFormat.format(DATA_UPDATE_MML, task.getFilePath(),
                task.getSourceVersion(), task.getTargetVersion());
    }

    @Override
    public Map<String, String> parseResult(String result) {
        return Utils.getMapInfo(result);
    }
}
