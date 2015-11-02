package com.holydota.common.log;

import java.lang.reflect.Constructor;

public class LogFactory {
    private static Constructor<? extends ILog> logConstructor;
    private static ThreadLocal<String>         flag = new ThreadLocal<String>();
    static {
        tryImplementation(new Runnable() {
            public void run() {
                try {
                    useSlf4jLogging();
                } catch (Exception e) {
                }
            }
        });
        tryImplementation(new Runnable() {
            public void run() {
                try {
                    useCommonsLogging();
                } catch (Exception e) {
                }
            }
        });
        tryImplementation(new Runnable() {
            public void run() {
                try {
                    useLog4JLogging();
                } catch (Exception e) {
                }
            }
        });
    }

    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                runnable.run();
            } catch (Exception e) {
            }
        }
    }

    public static synchronized void useSlf4jLogging() throws Exception {
        setImplementation(com.holydota.common.log.slf4j.Slf4jLogImpl.class);
    }

    public static synchronized void useCommonsLogging() throws Exception {
        setImplementation(com.holydota.common.log.commonlog.CommonLogImpl.class);
    }

    public static synchronized void useLog4JLogging() throws Exception {
        setImplementation(com.holydota.common.log.log4j.Log4jLogImpl.class);
    }

    private static void setImplementation(Class<? extends ILog> implClass) throws Exception {
        try {
            Constructor<? extends ILog> candidate = implClass.getConstructor(new Class[] { String.class });
            ILog logger = candidate.newInstance(new Object[] { LogFactory.class.getName() });
            logger.info("Logging initialized using '" + implClass + "' adapter.");
            logConstructor = candidate;
        } catch (Throwable t) {
            throw new LogException("Error setting Log implementation.  Cause: " + t, t);
        }
    }

    // disable construction
    private LogFactory() {
    }

    public static ILog getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    public static ILog getLog(String logger) {
        try {
            return logConstructor.newInstance(new Object[] { logger });
        } catch (Exception e) {
            throw new LogException("Error creating logger for logger " + logger + ".  Cause: " + e, e);
        }
    }

    /**
     * Setter method for property <tt>flag</tt>.
     * 
     * @param flag
     *            value to be assigned to property flag
     */
    public static void setFlag(String value) {
        flag.set(value);
    }

    /**
     * get method for property <tt>flag</tt>.
     * 
     * @param flag
     *            value to be get
     */
    public static String getFlag() {
        return flag.get();
    }

    public static void removeFlag() {
        flag.remove();
    }

    public static String getUniqueFlag() {
        return FlagGenerator.get().toStringBabble();
    }
}
