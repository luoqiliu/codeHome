package com.holydota.common.util;

import java.io.IOException;
import java.util.Properties;

public class LocalSettings {
    private static final Properties PROPERTIES = new Properties();

    static {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (null == cl) {
            cl = LocalSettings.class.getClassLoader();
        }
        try {
            PROPERTIES.load(cl.getResourceAsStream("localsettings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }

}
