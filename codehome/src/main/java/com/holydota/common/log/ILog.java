package com.holydota.common.log;

public interface ILog {

    /**
     * 日志级别判断
     * 
     * @return
     */
    boolean isDebugEnabled();

    /**
     * 日志级别判断
     * 
     * @return
     */
    public boolean isInfoEnabled();

    /**
     * 错误日志
     * 
     * @param message
     * @param e
     */
    void error(Object message, Throwable e);

    /**
     * 错误日志
     * 
     * @param message
     */
    void error(Object message);

    /**
     * debug日志
     * 
     * @param message
     */
    void debug(Object message);

    /**
     * debug日志
     * 
     * @param message
     * @param e
     */
    void debug(Object message, Throwable e);

    /**
     * info级别的日志
     * 
     * @param message
     */
    void info(Object message);

    /**
     * info级别的日志
     * 
     * @param message
     * @param e
     */
    void info(Object message, Throwable e);

    /**
     * warn 级别日志
     * 
     * @param message
     */
    void warn(Object message);

    /**
     * warn 级别日志
     * 
     * @param message
     * @param e
     */
    void warn(Object message, Throwable e);
}
