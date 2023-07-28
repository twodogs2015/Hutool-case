package com.twodog.Date;

import cn.hutool.core.date.*;

import java.util.Calendar;
import java.util.Date;

public class HutoolDateUtil {
    public static void main(String[] args) {
        parse();
    }

    public static void transition() {
        //当前时间
        Date date = DateUtil.date();
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();

        System.out.println(date);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(now);
        System.out.println(today);
    }

    public static void parse() {
        String dateStr = "2017-03-01T03:12:09";
        Date date = DateUtil.parse(dateStr);

        System.out.println(date);

        Date date2 = DateUtil.parse(dateStr, "yyyy-MM-dd");

        System.out.println(date2);

        System.out.println(DateUtil.now());
        Date now = DateTime.now();
        System.out.println(now);

    }

    public static void format() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);

        //结果 2017/03/01
        String format = DateUtil.format(date, DatePattern.NORM_DATE_PATTERN);

        //常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);

        //结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);

        //结果：00:00:00
        String formatTime = DateUtil.formatTime(date);

        System.out.println(format);
        System.out.println(formatDate);
        System.out.println(formatDateTime);
        System.out.println(formatTime);
    }

    public static void part() {
        Date date = DateUtil.date();
        //获得年的部分
        int year = DateUtil.year(date);
        //获得月份，从0开始计数
        int month = DateUtil.month(date);
        //获得月份枚举
        Month month1 = DateUtil.monthEnum(date);
        //.....

        System.out.println(date);
        System.out.println(year);
        System.out.println(month);
        System.out.println(month1);

    }

    public static void beginEnd() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);

        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);

        System.out.println(beginOfDay);
        System.out.println(endOfDay);

        System.out.println(DateUtil.between(endOfDay, beginOfDay, DateUnit.HOUR));
    }

    public static void offSet(){
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);

        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);

        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);


        System.out.println(date);
        System.out.println(newDate);
        System.out.println(newDate2);
        System.out.println(newDate3);
    }

    public static void off(){
        //昨天
        System.out.println(DateUtil.yesterday());
        //明天
        System.out.println(DateUtil.tomorrow());
        //上周
        System.out.println(DateUtil.lastWeek());
        //下周
        System.out.println(DateUtil.nextWeek());
        //上个月
        System.out.println(DateUtil.lastMonth());
        //下个月
        System.out.println(DateUtil.nextMonth());
    }
}
