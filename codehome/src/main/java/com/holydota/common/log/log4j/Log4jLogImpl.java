package com.holydota.common.log.log4j;

import org.apache.log4j.Logger;

import com.holydota.common.log.AbstractLogImpl;

public class Log4jLogImpl extends AbstractLogImpl {

    private Logger        logger;

    /** 错误日志 */
    private static Logger errorLogger = Logger.getLogger("errorLogger");

    public Log4jLogImpl(String clazz) {
        logger = Logger.getLogger(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void error(Object message, Throwable e) {
        String msg = warpMessage(message);
        logger.error(msg, e);
        errorLogger.error(msg, e);
    }

    @Override
    public void error(Object message) {
        String msg = warpMessage(message);
        if (message instanceof Throwable) {
            logger.error(msg, (Throwable) message);
            errorLogger.error(msg, (Throwable) message);
        } else {
            logger.error(msg);
            errorLogger.error(msg);
        }
    }

    @Override
    public void debug(Object message) {
        logger.debug(warpMessage(message));
    }

    @Override
    public void info(Object message) {
        logger.info(warpMessage(message));
    }

    @Override
    public void warn(Object message) {
        logger.warn(warpMessage(message));
    }

    @Override
    public void warn(Object message, Throwable e) {
        logger.warn(warpMessage(message), e);
    }

    @Override
    public void debug(Object message, Throwable e) {
        logger.debug(warpMessage(message), e);
    }

    @Override
    public void info(Object message, Throwable e) {
        logger.info(warpMessage(message), e);
    }
}
