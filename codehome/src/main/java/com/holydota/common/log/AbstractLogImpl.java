package com.holydota.common.log;

public abstract class AbstractLogImpl implements ILog {

    protected String warpMessage(Object message) {
        if (null == message) {
            return "";
        }
        return message.toString();

    }
}
