package com.chic.android.common;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author: chicochen
 * @date: 2018/4/14 下午3:04
 * @description: 日期工具类
 */

public class DateUtils {

    //默认日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    //中文日期格式
    public static final String DATE_CN_FORMAT = "yyyy年MM月dd日";
    //默认时间格式
    public static final String TIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //中文时间格式
    public static final String TIME_CN_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";


    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentTime() {
        return new Date();
    }

    /**
     * 获取当前时间
     *
     * @param pattern 指定格式
     * @return
     */
    public static String getCurrentTime(String pattern) {
        return formatDateToString(new Date(), pattern);
    }

    /**
     * 获取当前中文日期
     *
     * @return
     */
    public static String getCurrentDateCN() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "年" + month + "月" + day + "日";
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前天
     *
     * @return
     */
    public static int getCurrentDay() {
        Calendar d = Calendar.getInstance();
        return d.getActualMaximum(Calendar.DATE);
    }


    /**
     * Date格式化为时间戳
     *
     * @param date
     * @return 时间戳单位毫秒
     */
    public static long formatDateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 时间戳格式化为Date
     *
     * @param time
     * @return
     */
    public static Date formatLongToDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Date(time);
    }

    /**
     * 时间戳格式化为String
     *
     * @param time
     * @return
     */
    public static String formatLongToString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date(time);
        return sdf.format(date);
    }

    /**
     * Date格式化为String
     *
     * @param date    日期
     * @param pattern 格式
     * @return
     */
    public static String formatDateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    /**
     * String格式化为Date
     *
     * @param str
     * @param pattern
     * @return
     */
    public static Date formatStringToDate(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Calendar格式化为String
     *
     * @param calendar
     * @return
     */
    public static String formatCalendarToString(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }


    /**
     * String格式化为Calendar
     *
     * @param str
     * @return
     */
    public static Calendar formatStringToCalendar(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Date格式化为Calendar
     *
     * @param date
     * @return
     */
    public static Calendar formatDateToCalender(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Calendar格式化为Date
     *
     * @param calendar
     * @return
     */
    public static Date formatCalenderToDate(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * String格式化为String
     *
     * @param day
     * @param curPattern
     * @param spePattern
     * @return
     */
    public static String formatStringToString(String day, String curPattern, String spePattern) {
        return formatDateToString(formatStringToDate(day, curPattern), spePattern);
    }

    /**
     * 是否是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        boolean flag = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        return flag;
    }

    /**
     * 是否闰年
     *
     * @param date
     * @return
     */
    public static boolean isLeapYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        boolean flag = isLeapYear(cal.get(Calendar.YEAR));
        return flag;
    }


    /**
     * 比较日期大小
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int compareDate(String str1, String str2) {
        Date date1 = formatStringToDate(str1, TIME_DEFAULT_FORMAT);
        Date date2 = formatStringToDate(str2, TIME_DEFAULT_FORMAT);
        return date1.compareTo(date2);
    }


    /**
     * 计算给定日期当年总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算给定日期当年剩余天数
     *
     * @param date
     * @return
     */
    public static int getDaysLeftOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int cnt = cal.getActualMaximum(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
        return cnt;
    }


    /**
     * 获得星期几(周日为1，周六为7)
     *
     * @param date 给定日期
     * @return
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得星期几(中文)
     *
     * @param date
     * @return
     */
    public static String getWeekCN(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }


    /**
     * 计算给定日期所在月的第一天
     *
     * @param date 给定日期
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 计算给定日期所在月的最后一天
     *
     * @param date 给定日期
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * 获取当前日期增加或较少n天的日期
     *
     * @param days 正数增加，反之减少
     * @return
     */
    public static String getDateOfAddDay(int days) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, days);
        SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        Date date = calendar1.getTime();
        return format.format(date);
    }


    /**
     * 获取指定日期增加或较少n天的日期
     *
     * @param str
     * @param days 正数增加，反之减少
     * @return
     */
    public static String getDateOfAddDay(String str, int days) {
        Calendar cal = formatStringToCalendar(str);
        cal.add(Calendar.DATE, days);
        SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        Date date = cal.getTime();
        return format.format(date);
    }

    /**
     * 获取指定日期加月加天的日期
     *
     * @param str
     * @param months
     * @param days
     * @return
     */
    public static String getDateByAddDayAndMonth(String str, int months, int days) {
        Calendar cal = formatStringToCalendar(str);
        cal.add(Calendar.MONTH, months);
        cal.add(Calendar.DATE, days);
        SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        Date date = cal.getTime();
        return format.format(date);
    }


    /**
     * 获取指定日期和当前日期相隔天数
     *
     * @param str
     * @return
     */
    public static long getDayWithCurrent(String str) {
        Calendar calendar = formatStringToCalendar(str);
        Calendar calendar1 = Calendar.getInstance();
        long millis = Math.abs(calendar.getTimeInMillis() - calendar1.getTimeInMillis());
        return millis / (24L * 60L * 60L * 1000L);
    }

    /**
     * 获取两个日期相隔的天数
     *
     * @param from
     * @param to
     * @return
     */
    public static long getDaysBetweenDate(String from, String to) {
        Calendar fromCalendar = formatStringToCalendar(from);
        Calendar toCalendar = formatStringToCalendar(to);
        long millis = Math.abs(fromCalendar.getTimeInMillis() - toCalendar.getTimeInMillis());
        return millis / (24L * 60L * 60L * 1000L);
    }

    /**
     * 判断给定日期是否在当前日期之前
     *
     * @param str
     * @return
     */
    public static boolean isBefore(String str) {
        Calendar calendar = formatStringToCalendar(str);
        return calendar.before(Calendar.getInstance());
    }

    /**
     * 获取指定日期月有多少天
     *
     * @param str
     * @return
     */
    public static int getDaysOfMonth(String str) {
        Calendar calendar = formatStringToCalendar(str);
        switch (calendar.get(Calendar.MONTH) + 1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(calendar.get(Calendar.YEAR))) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }


    /**
     * 获取指定日期在一年中的第几个星期
     *
     * @param str
     * @return
     */
    public static int getWeekOfYear(String str) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取指定日期在一年中的第几天
     *
     * @param str
     * @return
     */
    public static int getDayOfYear(String str) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }


    /**
     * 获取指定日期的年龄
     *
     * @param day
     * @return
     */
    public static int getAgeOfDate(String day) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatDate = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
            String currentTime = formatDate.format(calendar.getTime());
            Date today = formatDate.parse(currentTime);
            Date brithDay = formatDate.parse(day);
            if ((today.getYear() - brithDay.getYear()) > 100) {
                return 0;
            } else {
                return today.getYear() - brithDay.getYear();
            }
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 判断是否是今天
     *
     * @param day
     * @return
     */
    public static boolean isToday(String day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar today = Calendar.getInstance();
        Calendar current = Calendar.getInstance();
        current.setTime(date);

        return today.get(Calendar.YEAR) == current.get(Calendar.YEAR)
                && today.get(Calendar.MONTH) == current.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) == current.get(Calendar.DAY_OF_MONTH);
    }
}
