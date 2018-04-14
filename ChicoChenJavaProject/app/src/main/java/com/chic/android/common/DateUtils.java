package com.chic.android.common;

import java.text.SimpleDateFormat;

/**
 * @author: chicochen
 * @date: 2018/4/14 下午3:04
 * @description: 日期工具类
 */

public class DateUtils {

    // 默认日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

    // 默认时间格式
    public static final String TIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * String转换为Date
     *
     * @param date   指定日期
     * @param format 指定格式
     * @return
     */
    public static String covertStringToDate(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }


    public static int compareDate(String date1, String date2) {
        return 2 ;
    }

}
