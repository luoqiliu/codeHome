package com.holydota.common.util;

public final class TimeKeys {
    //**************************************************************************************/
    /**
     * 一秒钟大小
     */
    public static final long   SECOND_MILLIS             = 1000;

    /**
     * 一分钟大小
     */
    public static final long   MINUTE_MILLIS             = 60 * SECOND_MILLIS;

    /**
     * 一小时大小
     */
    public static final long   HOUR_MILLIS               = 60 * MINUTE_MILLIS;

    /**
     * 一天大小
     */
    public static final long   DAY_MILLIS                = 24 * HOUR_MILLIS;

    /**
     * 半小时大小
     */
    public static final long   HALF_HOUR_MILLIS          = 30 * MINUTE_MILLIS;

    /**
     * 一分钟大小
     */
    public static final int    MINUTE_SECONDS            = 60;

    /**
     * 一小时大小
     */
    public static final int    HOUR_SECONDS              = 60 * MINUTE_SECONDS;

    /**
     * 一天大小
     */
    public static final int    DAY_SECONDS               = 24 * HOUR_SECONDS;
    //**************************************************************************************/

    //**************************************************************************************/
    /**
     * 时间格式1：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String YYYY_MM_DD_HH_MM_SS_SSS   = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 时间格式2： yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYY_MM_DD_HH_MM_SS       = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式3： yyyyMMddHHmmss
     */
    public static final String YYYYMMDDHHMMSS            = "yyyyMMddHHmmss";

    /**
     * 时间格式4： yyyy-MM-dd
     */
    public static final String YYYY_MM_DD                = "yyyy-MM-dd";

    /**
     * 时间格式5： yyyyMMdd
     */
    public static final String YYYYMMDD                  = "yyyyMMdd";

    /**
     * 时间格式6： HH:mm:ss
     */
    public static final String HH_MM_SS                  = "HH:mm:ss";

    /**
     * 时间格式7：MMddHHmm
     */
    public static final String MMDDHHMM                  = "MMddHHmm";

    /**
     * 时间格式8： HHmmss
     */
    public static final String HHMMSS                    = "HHmmss";

    /**
     * 时间格式：HHmmssSSS
     */
    public static final String HHMMSSSSS                 = "HHmmssSSS";

    /**
     * 时间格式9：yyMMddHHmmssSSS
     */
    public static final String YYYYMMDDHHMMSSSSS         = "yyyyMMddHHmmssSSS";

    /**
     * 全时间格式10： yyyy-MM-dd HH:mm:ss.SSS a Z E D w
     */
    public static final String YYYY_MM_DD_HH_MM_SS_FULL  = "yyyy-MM-dd HH:mm:ss.SSS a Z E D w";

    /**
     * 时间格式11： yyyy.MM.dd
     */
    public static final String YYYY_MM_DD_EX1            = "yyyy.MM.dd";

    /**
     * JSON时间格式
     */
    public static final String YYYY_MM_DD_T_HH_MM_SS     = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Timezone时间格式
     */
    public static final String YYYY_MM_DD_T_HH_MM_SS_Z   = "yyyy-MM-dd'T'HH:mm:ssz";

    /**
     * 年份
     */
    public static final String YYYY                      = "yyyy";

    /**
     * 月份
     */
    public static final String MM                        = "MM";

    /**
     * 日
     */
    public static final String DD                        = "dd";

    /**
     * 小时
     */
    public static final String HH                        = "HH";

    /**
     * 分钟
     */
    public static final String MM_S                      = "mm";

    /**
     * 秒钟
     */
    public static final String SS                        = "ss";

    /**
     * 年周
     */
    public static final String YYYYW                     = "yyyyw";

    /**
     * 年周
     */
    public static final String YYYY_W                    = "yyyy w";
    //**************************************************************************************/

    //**************************************************************************************/
    /**
     * 日期时间
     */
    public static final String REGEX_YYYY_MM_DD          = "\\d{4}-\\d{1,2}-\\d{1,2}";

    /**
     * 日期时间
     */
    public static final String REGEX_YYYY_MM_DD_HH_MM_SS = "\\d{4}-\\d{1,2}-\\d{1,2} ([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

    /**
     * 日期时间
     */
    public static final String REGEX_YYYYMMDD            = "\\d{4}\\d{1,2}\\d{1,2}";

    /**
     * 日期时间
     */
    public static final String REGEX_YYYYMMDDHHMMSS      = "\\d{4}\\d{1,2}\\d{1,2}([01][0-9]|2[0-3])[0-5][0-9][0-5][0-9]";

    /**
     * 时间格式
     */
    public static final String REGEX_HH_MM_SS            = "([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

    /**
     * 时间格式
     */
    public static final String REGEX_YYYY_MM_DD_EX1      = "\\d{4}\\.\\d{1,2}\\.\\d{1,2}";

    /**
     * 月份时间格式
     */
    public static final String REGEX_YYYY_MM             = "\\d{4}-\\d{1,2}";

    /**
     * 常理类，私有化构造方法
     */
    private TimeKeys() {

    }
}
