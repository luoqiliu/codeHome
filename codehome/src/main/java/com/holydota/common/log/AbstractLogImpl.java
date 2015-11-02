package com.holydota.common.log;

public abstract class AbstractLogImpl implements ILog {

    protected String warpMessage(Object message) {
        if (null == message) {
            return "";
        }
        String flag = LogFactory.getFlag();

        if (flag == null || flag.isEmpty()) {
            return message.toString();
        }

        return message + ",Flag=" + flag;

    }
}
