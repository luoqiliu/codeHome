package com.holydota.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * <功能详细描述>时间工具类
 * @author yanjun.li
 *
 */
public final class TimeUtils {
    /**
     * 时间正则表达式与格式对应关系 
     */
    private static final String[][] COM_PATTERNS     = new String[][] {
            new String[] { TimeKeys.REGEX_YYYY_MM_DD, TimeKeys.YYYY_MM_DD },
            new String[] { TimeKeys.REGEX_YYYY_MM_DD_HH_MM_SS, TimeKeys.YYYY_MM_DD_HH_MM_SS },
            new String[] { TimeKeys.REGEX_YYYYMMDD, TimeKeys.YYYYMMDD },
            new String[] { TimeKeys.REGEX_YYYYMMDDHHMMSS, TimeKeys.YYYYMMDDHHMMSS },
            new String[] { TimeKeys.REGEX_HH_MM_SS, TimeKeys.HH_MM_SS } };

    /**
     * 特殊时间正则表达式与格式对应关系
     */
    private static final String[][] SPECIAL_PATTERNS = new String[][] {
                                                     //调用中音的qryUserTone的时候有些局点返回的时间格式是yyyy.MM.dd
                                                     new String[] { TimeKeys.REGEX_YYYY_MM_DD_EX1,
            TimeKeys.YYYY_MM_DD_EX1                 } };

    /**
     * 工具类，私有化构造方法
     */
    private TimeUtils() {

    }

    /**
     * 对输入时间进行加法操作（因子可以为负数，此时表减法）
     * @param inputTime 输入时间
     * @param field 加法类型
     * 1、Calendar.MONTH 按月份
     * 2、Calendar.DATE  按天
     * 3、Calendar.MINUTE  --按分钟
     * @param amount 增加幅度 负数则为减法
     * @return 加法后的时间
     */
    public static long addTime(long inputTime, int field, int amount) {
        //日期方法
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(inputTime));
        calendar.add(field, amount);
        return calendar.getTimeInMillis();
    }

    /**
     * 在原有日期上加上指定天数
     * @param inputTimeStr 原来日期String型
     * @param timePattern 时间格式
     * @param field 加法类型
     * 1、Calendar.MONTH 按月份
     * 2、Calendar.DATE  按天
     * 3、Calendar.MINUTE  --按分钟
     * @param amount 增加幅度 负数则为减法
     * @author g00192941
     * @return date
     */
    public static String addTime(String inputTimeStr, String timePattern, int field, int amount) {
        //获取输入时间的长整型值
        long inputTime;
        try {
            inputTime = parseTimeInMillis(inputTimeStr, timePattern);
        } catch (ParseException e1) {
            return null;
        }

        //获取时间加减后的值
        long addedInputTime = addTime(inputTime, field, amount);

        //对时间进行字符串格式化
        return formatTime(addedInputTime, timePattern);
    }

    /**
     * 数据库时间格式转换
     * @param inputTime 字符串时间
     * @return long型时间
     * @throws ParseException 解析异常
     */
    public static long parseTimeInMillis(String inputTime) throws ParseException {
        //默认不支持特殊格式的时间格式
        return parseTimeInMillis(inputTime, false);
    }

    /**
     * 数据库时间格式转换
     * @param inputTime 字符串时间
     * @param specialPattern 特殊格式
     * @return long型时间
     * @throws ParseException 解析异常
     */
    public static long parseTimeInMillis(String inputTime, boolean specialPattern) throws ParseException {
        //判断输入时间是否为空，为空直接返回码
        if (StringUtils.isBlank(inputTime)) {
            throw new ParseException("Empty param value input!", 0);
        }

        //根据输入时间获取格式
        String pattern = getTimePattern(inputTime, TimeKeys.HH_MM_SS, specialPattern);

        //解析
        return parseTimeInMillis(inputTime, pattern);
    }

    /**
     * 格式化字符串时间
     * @param inputTime 字符串时间
     * @param pattern 格式类型
     * @return 格式化后的时间（long型）
     * @throws ParseException 格式化异常
     */
    public static long parseTimeInMillis(String inputTime, String pattern) throws ParseException {
        //初始化格式初始工具
        final DateFormat dateFormat = new SimpleDateFormat(pattern);

        //格式化字符串
        final Date inputDate = dateFormat.parse(inputTime);

        //格式化字符串
        return inputDate.getTime();
    }

    /**
     * 根据时间值获取时间格式
     * @param inputTime 时间的值
     * @param defPattern 默认格式
     * @param specialPattern 特殊格式
     * @return 时间格式
     */
    private static String getTimePattern(String inputTime, String defPattern, boolean specialPattern) {
        //循环列表进行判断处理
        int length = COM_PATTERNS.length;
        for (int i = 0; i < length; i++) {
            //判断是否匹配正则表达式
            if (inputTime.matches(COM_PATTERNS[i][0])) {
                return COM_PATTERNS[i][1];
            }
        }

        //不包括特殊格式则直接返回
        if (!specialPattern) {
            return defPattern;
        }

        //循环遍历特殊格式列表
        length = SPECIAL_PATTERNS.length;
        for (int i = 0; i < length; i++) {
            //判断是否匹配正则表达式
            if (inputTime.matches(SPECIAL_PATTERNS[i][0])) {
                return SPECIAL_PATTERNS[i][1];
            }
        }

        //其他情况返回默认值
        return defPattern;
    }

    /**
     * 将Long型时间转换为字符串
     * @param inputTime long型时间
     * 当前时间数目或者the milliseconds since January 1, 1970, 00:00:00 GMT.
     * @param pattern 类型
     * @return 返回字符串时间
     */
    public static String formatTime(long inputTime, String pattern) {
        //返回格式化后的字符串时间
        return new SimpleDateFormat(pattern).format(new Date(inputTime));
    }

    /**
     * 将某格式字符串类型时间转换为另一个格式字符串
     * @param inputTime 字符串型时间
     * @param inputPattern 输入时间的格式
     * @param outputPattern 输出时间的格式
     * @return 返回字符串时间
     */
    public static String formatTime(String inputTime, String inputPattern, String outputPattern) {
        Date date = parseTime0(inputTime, inputPattern);
        //返回格式化后的字符串时间
        return new SimpleDateFormat(outputPattern).format(date);
    }

    /**
     * 将日期型时间转换为字符串
     * @param date 日期型时间
     * @param pattern 类型
     * @return 返回字符串时间
     */
    public static String formatTime(Date date, String pattern) {
        if (null == date) {
            return "";
        }
        //返回格式化后的字符串时间
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 得到当前日期和时间
     * @param pattern 格式类型
     * @return 当前日期和时间
     */
    public static String getJavaThisTime(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * 获取今天开始时间的毫秒值
     * @return 今天开始时间的毫秒值
     */
    public static long getJavaTodayStartTime() {
        //获取JAVA系统当前时间
        final Calendar calendar = Calendar.getInstance();

        //使用内部方法获取今天的开始时间
        return getCalendarDayTopTime(calendar, false).getTimeInMillis();
    }

    /**
     * 获取今天结束时间的毫秒值
     * @return 今天结束时间的毫秒值
     */
    public static long getJavaTodayEndTime() {
        //获取JAVA系统当前时间
        final Calendar calendar = Calendar.getInstance();

        //使用内部方法获取今天的开始时间
        return getCalendarDayTopTime(calendar, true).getTimeInMillis();
    }

    /**
     * 获取今天开始时间的毫秒值
     * @param calendar0 日历对象
     * @param isEndDay 是否获取天的结束时间
     * @return 返回日历对象一天的开始或结束时间
     */
    public static Calendar getCalendarDayTopTime(Calendar calendar0, boolean isEndDay) {
        //检查日历对象
        final Calendar calendar = null == calendar0 ? Calendar.getInstance(Locale.ENGLISH) : calendar0;

        //清除当前的小时、分钟、秒 和 毫秒
        calendar.set(Calendar.MILLISECOND, 0);//清除毫秒位数字
        calendar.set(Calendar.SECOND, 0);//清除秒钟位数字
        calendar.set(Calendar.MINUTE, 0);//清除分钟位数字
        calendar.set(Calendar.HOUR_OF_DAY, 0);//清除小时位数字

        //判断是否为结束时间
        if (isEndDay) {
            //时间向后增加一天
            calendar.add(Calendar.DATE, 1);
            //退后一天的当天开始时间再减去一毫秒，则为当天的最后时间
            calendar.add(Calendar.MILLISECOND, -1);
        }

        //返回日历对象
        return calendar;
    }

    /**
     * 得到昨天当前日期和时间
     * @param pattern 格式类型
     * @return 当前日期和时间
     */
    public static String getJavaYesterdayThisTime(String pattern) {
        //获取系统当前时间
        long thisTime = System.currentTimeMillis();

        //获取昨天的当前时间
        long yesterdayThisTime = addTime(thisTime, Calendar.DATE, -1);

        //长整型时间转字符串
        return formatTime(yesterdayThisTime, pattern);
    }

    /**
     * 转换时间格式
     * @param inputTime 时间参数
     * @param toFormat 目标格式
     * @return 目标格式的时间
     */
    public static String switchTimeFormat(String inputTime, String toFormat) {
        //默认不支持特殊格式的时间格式进行检查跳转
        return switchTimeFormat(inputTime, toFormat, false);
    }

    /**
     * 转换时间格式
     * @param inputTime 时间参数
     * @param toFormat 目标格式
     * @param specialPattern 特殊格式
     * @return 目标格式的时间
     */
    public static String switchTimeFormat(String inputTime, String toFormat, boolean specialPattern) {
        //判断时间是否为空，为空直接返回
        if (StringUtils.isBlank(inputTime) || StringUtils.isBlank(toFormat)) {
            return inputTime;
        }

        //根据输入时间获取格式
        String fromFormat = getTimePattern(inputTime, toFormat, specialPattern);

        //判断目标格式与当前格式是否一致,则直接返回
        if (toFormat.equals(fromFormat)) {
            return inputTime;
        }

        //进行时间格式转换
        return switchTimeFormat(inputTime, fromFormat, Locale.getDefault(), toFormat, Locale.getDefault());
    }

    /**
     * 从一个时间格式转换到另一个时间格式
     * @param dateTime  需要改变的时间
     * @param fromFormat 原来的时间格式
     * @param toFormat 目标时间格式
     * @return 将原来的时间格式转换成需要的时间格式字符串
     */
    public static String switchTimeFormat(String dateTime, String fromFormat, String toFormat) {
        //使用默认语言类型进行转换
        return switchTimeFormat(dateTime, fromFormat, Locale.getDefault(), toFormat, Locale.getDefault());
    }

    /**
     * 从一个时间格式转换到另一个时间格式
     * @param dateTime  需要改变的时间
     * @param fromFormat 原来的时间格式
     * @param fromLocale 语言类型
     * @param toFormat 目标时间格式
     * @param toLocale  语言类型
     * @return 将原来的时间格式转换成需要的时间格式字符串
     */
    public static String switchTimeFormat(String dateTime, String fromFormat, Locale fromLocale, String toFormat,
                                          Locale toLocale) {
        //判断时间参数是否为空，为空直接返回
        if (StringUtils.isBlank(dateTime)) {
            return dateTime;
        }

        //源格式
        final DateFormat from = new SimpleDateFormat(fromFormat, fromLocale);
        //目的格式
        final DateFormat to = new SimpleDateFormat(toFormat, toLocale);

        try {
            final Date date = from.parse(dateTime);
            return to.format(date);
        } catch (ParseException e) {
            return dateTime;
        }
    }

    /**
     * 检查字符串时间是为指定格式
     * @param inputTime 字符串格式的时间
     * @param pattern 类型
     * @return 是否指定格式的时间
     */
    public static boolean validateTimePattern(String inputTime, String pattern) {
        //初始化格式初始工具
        final DateFormat dateFormat = new SimpleDateFormat(pattern);
        //设置为强校验
        dateFormat.setLenient(false);

        try {
            //解析字符串时间
            dateFormat.parse(inputTime);
        } catch (ParseException e) {
            return false;
        }

        //返回成功
        return true;
    }

    /**
     * 检查字符串时间是为指定格式
     * @param inputTime 字符串格式的时间
     * @param pattern 类型
     * @return 是否指定格式的时间
     */
    public static boolean validateForceTimePattern(String inputTime, String pattern) {
        //判断时间内容是否为空
        if (StringUtils.isBlank(inputTime) || null == pattern) {
            return false;
        }

        //判断字符串长度是否一致
        if (inputTime.length() != pattern.length()) {
            return false;
        }

        //格式校验
        return validateTimePattern(inputTime, pattern);
    }

    /**
     * UTC日期对象与Local日历对象进行跳转
     * @param calendar 日历对象
     * @param localToUTC 是否本地转UTC
     * @return 新的日历对象
     */
    private static Calendar switchUTCAndLocal(Calendar calendar, boolean localToUTC) {
        //获取时区域
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);//以毫秒为单位指示距 GMT 的大致偏移量
        int destOffset = calendar.get(Calendar.DST_OFFSET);//以毫秒为单位指示夏令时的偏移量

        switchTimeZoneToLocal(calendar, localToUTC, zoneOffset, destOffset);

        //返回当前日历对象
        return calendar;
    }

    /**
     * TimeZone日期对象与Local日历对象进行跳转
     * @param calendar 日历对象
     * @param localToUTC 是否本地转TimeZone
     * @return 新的日历对象
     */
    private static Calendar switchTimeZoneToLocal(Calendar calendar, boolean localToTimeZone, int zoneOffset,
                                                  int destOffset) {
        //获取偏移量值(小时值)
        int hourOffset = (int) ((zoneOffset - destOffset) / TimeKeys.HOUR_MILLIS);

        //获取现有的小时数值
        int curHours = calendar.get(Calendar.HOUR_OF_DAY);

        //获取系统新的小时值
        int newCurHours = localToTimeZone ? curHours - hourOffset : curHours + hourOffset;

        //更改时间为标准时间
        calendar.set(Calendar.HOUR_OF_DAY, newCurHours);

        //返回当前日历对象
        return calendar;
    }

    /**
     * 转换UTC至本地日历
     * @param utcCalendar UTC日历对象
     * @return 本地日历对象
     */
    public static Calendar changeUTCToLocal(Calendar utcCalendar) {
        return null == utcCalendar ? null : switchUTCAndLocal(utcCalendar, false);
    }

    /**
     * 转换本地至UTC日历
     * @param localCalendar 本地 日历对象
     * @return UTC日历对象
     */
    public static Calendar changeLocalToUTC(Calendar localCalendar) {
        return null == localCalendar ? null : switchUTCAndLocal(localCalendar, true);
    }

    /**
     * 获取系统当前UTC日历对象
     * @return 系统当前UTC日历对象
     */
    public static Calendar getThisUTCCalendar() {
        //获取本地系统日历对象
        Calendar calendar = Calendar.getInstance();

        //日历对象转换为UTC
        return switchUTCAndLocal(calendar, true);
    }

    /**
     * 获取格式为yyyy-MM-dd HH:mm:ssUTC当前时间
     * @return UTC当前时间
     */
    public static String getUTCThisTime() {
        return getUTCThisTime(TimeKeys.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取系统当前时间
     * @param pattern 指定格式
     * @return 系统当前时间
     */
    public static String getUTCThisTime(String pattern) {
        //日历对象转换为UTC
        Calendar calendarUTC = getThisUTCCalendar();

        //日历信息转字符串
        return formatTime(calendarUTC.getTime(), pattern);
    }

    /**
     * 将格林威治时间转化成本地时间,返回格式为String
     * 格林威治时间+8小时=北京时间
     * @param utcTime 传入的格林威治
     * @param pattern 输入输出时间格式
     * @return 本地时间
     */
    public static String changeUTCToLocalString(String utcTime, String pattern) {
        return changeUTCToLocalString(utcTime, pattern, pattern);
    }

    /**
     * 将带时区的时间转化成本地时间,返回格式为String
     * @param timeZoneStr 输入带有时区的时间：yyyy-MM-ddTHH:mm:ssZ，
     * 如：2012-12-31T00:00:00+08:00和2012-12-31T00:00:00+0800都是合法的时间格式
     * @param outputPattern 输出时间格式
     * @return 本地时间
     */
    public static String changeTimeZoneToLocalString(String timeZoneStr, String outputPattern) {
        //判断是否为空
        if (null == timeZoneStr) {
            return null;
        }

        String inputTimeStr = timeZoneStr;

        //若时间的时区如（+08:00)带有:，则把:去除
        if (inputTimeStr.length() > 5 && inputTimeStr.substring(inputTimeStr.length() - 5).indexOf(":") >= 0) {
            inputTimeStr = inputTimeStr.substring(0, inputTimeStr.length() - 5)
                           + inputTimeStr.substring(inputTimeStr.length() - 5).replace(":", "");
        }

        //日期显示格式
        SimpleDateFormat format = new SimpleDateFormat(TimeKeys.YYYY_MM_DD_T_HH_MM_SS_Z);

        //获取日历对象
        Calendar timeZoneCalendar = Calendar.getInstance();

        //本地的时区便宜量
        int localTimeZoneOffset = timeZoneCalendar.get(Calendar.ZONE_OFFSET);

        try {
            timeZoneCalendar.setTime(format.parse(inputTimeStr));

            //输入时区的偏移量
            int timeZoneOffset = timeZoneCalendar.get(Calendar.ZONE_OFFSET);

            Calendar returnCalendar = switchTimeZoneToLocal(timeZoneCalendar, false, localTimeZoneOffset,
                timeZoneOffset);

            return new SimpleDateFormat(outputPattern).format(returnCalendar.getTime());
        } catch (ParseException e) {
            return TimeUtils.switchTimeFormat(timeZoneStr, TimeKeys.YYYY_MM_DD_T_HH_MM_SS, outputPattern);
        }
    }

    /**
     * 将格林威治时间转化成本地时间,返回格式为String
     * 格林威治时间+8小时=北京时间
     * @param utcTime 传入的格林威治
     * @param inputPattern 输入时间格式
     * @param outputPattern 输出时间格式
     * @return 本地时间
     */
    public static String changeUTCToLocalString(String utcTime, String inputPattern, String outputPattern) {
        //判断输入的UTC字符串是否为空
        if (StringUtils.isBlank(utcTime)) {
            return null;
        }

        //日期显示格式
        SimpleDateFormat format = new SimpleDateFormat(inputPattern);
        Date utcDate = format.parse(utcTime, new ParsePosition(utcTime.indexOf("")));

        //获取日历对象
        Calendar utcCalendar = Calendar.getInstance();
        utcCalendar.setTime(utcDate);

        //获取本地日历对象
        Calendar localCalendar = switchUTCAndLocal(utcCalendar, false);

        //转换时间为指定格式
        return new SimpleDateFormat(outputPattern).format(localCalendar.getTime());
    }

    /**
     * 将本地时间转化为UTC（格林威治）时间,返回格式为Calendar
     * 格林时间+8小时=北京时间
     * 说明：该方法不能自行删除，soap接口测试页面再使用
     * @param localTime 传入的本地时间
     * @param pattern 格式类型  时间 格式yyyy-MM-dd HH:mm:ss
     * @return UTC（格林威治）时间
     */
    public static Calendar changeToUTCCalendar(String localTime, String pattern) {
        //判断输入的UTC字符串是否为空
        if (StringUtils.isBlank(localTime)) {
            return null;
        }

        //获取系统当前日历对象
        Calendar localCalendar = Calendar.getInstance();
        // 日期显示格式
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date localDate = format.parse(localTime, new ParsePosition(localTime.indexOf("")));
        //把时间放到日历表中
        localCalendar.setTime(localDate);

        // 返回UTC时间格式
        return switchUTCAndLocal(localCalendar, true);
    }

    /**
     * 将本地时间转化为UTC（格林威治）时间
     * 格林时间+8小时=北京时间
     * @param localTime 传入的本地时间 格式yyyy-MM-dd HH:mm:ss
     * @param pattern 输入输出时间格式
     * @return UTC（格林威治）时间 格式yyyy-MM-dd HH:mm:ss
     */
    public static String changeLocalToUTCString(String localTime, String pattern) {
        return changeLocalToUTCString(localTime, pattern, pattern);
    }

    /**
     * 将本地时间转化为UTC（格林威治）时间
     * 格林时间+8小时=北京时间
     * @param localTime 传入的本地时间 格式yyyy-MM-dd HH:mm:ss
     * @param inputPattern 输入时间格式
     * @param outputPattern 输出时间格式
     * @return UTC（格林威治）时间 格式yyyy-MM-dd HH:mm:ss
     */
    public static String changeLocalToUTCString(String localTime, String inputPattern, String outputPattern) {
        Calendar calendar = changeToUTCCalendar(localTime, inputPattern);

        //返回UTC时间格式
        return null == calendar ? null : new SimpleDateFormat(outputPattern).format(calendar.getTime());
    }

    /**
     * 将Long型时间转换为字符串
     * @param inputTime long型时间
     * @param pattern 类型
     * @return 返回字符串时间
     */
    public static String parseTimeToStr(final long inputTime, final String pattern) {
        //初始化格式初始工具
        final DateFormat dateFormat = new SimpleDateFormat(pattern);

        //获取时间对象
        final Date date = new Date(inputTime);

        //返回格式化后的字符串时间
        return dateFormat.format(date);
    }

    /**
     * 解析事件内部方法
     * @param inputTime 输入时间
     * @param pattern 格式信息
     * @return 返回码对象
     */
    public static Date parseTime0(String inputTime, String pattern) {
        //判断输入时间是否为空
        if (StringUtils.isBlank(inputTime)) {
            return null;
        }

        //初始化指定格式的初始工具
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(inputTime);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 事件比较方法
     * @param leftTime 左时间
     * @param rightTime 右事件
     * @param pattern 时间格式
     * @return 整型 -1：（leftTime < rightTime）,0:相等； 1：（leftTime > rightTime）
     */
    public static int compareTime(String leftTime, String rightTime, String pattern) {
        //格式化字符串
        Date leftDate = parseTime0(leftTime, pattern);
        Date rightDate = parseTime0(rightTime, pattern);

        //判断时间对象是否为空
        if (null == leftDate || null == rightDate) {
            return null == leftDate ? ((null == rightDate) ? 0 : -1) : 1;
        }

        //使用时间比较方法
        return leftDate.compareTo(rightDate);
    }

    /**
     * 获取本月第一天
     * @param pattern 时间格式
     * @return 当月第一天
     */
    public static String getFirstDayThisMonth(String pattern) {
        //若格式为空时，赋默认值
        if (StringUtils.isBlank(pattern)) {
            pattern = TimeKeys.YYYY_MM_DD;
        }
        Calendar calendar = Calendar.getInstance();

        //设置当前日期
        calendar.setTime(new Date());

        //取得当前月的最小日期(天)
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);

        //格式化成字符串
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    /**
     * 获取本月最后一天
     * @param pattern 时间格式
     * @return 本月最后一天
     */
    public static String getLastDayThisMonth(String pattern) {
        //若格式为空时，赋默认值
        if (StringUtils.isBlank(pattern)) {
            pattern = TimeKeys.YYYY_MM_DD;
        }
        Calendar calendar = Calendar.getInstance();

        //设置当前日期
        calendar.setTime(new Date());

        //取得当前月的最大日期(天)
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);

        // 格式化成字符串
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    /**
     * 获取以毫秒的形式获取UTC当前时间
     * @return 以毫秒的形式的UTC当前时间
     */
    public static long getUTCTimeInMillis() {
        //1、取得本地时间 
        Calendar calendar = Calendar.getInstance();

        //2、取得时间偏移量     
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);

        //3、取得夏令时差  
        int dstOffset = calendar.get(Calendar.DST_OFFSET);

        //4、从本地时间里扣除这些差量，即可以取得UTC时间    
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        return calendar.getTimeInMillis();
    }

    /**
     * 获取某月天数
     * @param month 输入的月份 格式为yyyy-mm
     * @return 本月天数
     */
    public static int getDaysOfMonth(String month) {
        Calendar calendar = Calendar.getInstance();

        calendar.clear();

        //如果month为空 返回当前月天数
        if (StringUtils.isBlank(month) || !month.matches(TimeKeys.REGEX_YYYY_MM)) {
            //取得当前月的最大日期(天)
            return -1;
        }

        String[] date = month.split("-", -1);

        //设置当前年
        calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));

        //设置当前月
        calendar.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);

        //取得当前月的最大日期(天)
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前月的上下几个月的月份 格式：yyyy-mm
     * @param month 输入的月份 格式为yyyy-mm
     * @param addCount 差值
     * @return 当前月的上下几个月的月份 格式：yyyy-mm
     */
    public static String getAddMonth(String month, int addCount) {
        Calendar calendar = Calendar.getInstance();

        calendar.clear();

        //如果month为空 返回当前月天数
        if (StringUtils.isBlank(month) || !month.matches(TimeKeys.REGEX_YYYY_MM)) {
            //返回空值
            return StringUtils.EMPTY;
        }

        //日期显示格式
        SimpleDateFormat format = new SimpleDateFormat(TimeKeys.YYYY_MM_DD);

        //设置某月月初日期
        try {
            calendar.setTime(format.parse(month + "-01"));

            //得到前后几个月
            calendar.add(Calendar.MONTH, addCount);

            //取得当前月的最大日期(天)
            return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1);
        } catch (ParseException e) {
            //返回空值
            return StringUtils.EMPTY;
        }
    }
}
