package com.zhenhua.microread.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zzh
 * @create 2019-08-24 14:09
 * @desc 时间工具类
 */
public class DateUtil {

    public static String pattern = "yyyy-MM-dd";
    public static SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

    public static DateTimeFormatter getDateTimeFormatter() {
        return dateFormatter;
    }

    /**
     * 获得当前日期
     *
     * @return
     */
    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
//        Date currDate = new Date();
        return currDate;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        ParsePosition  pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        String dateString = formatter.format(new Date());
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyyMMddHHmmss
     */
    public static String getStringAllDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(new Date());
        return dateString;
    }
}
