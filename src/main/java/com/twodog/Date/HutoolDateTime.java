package com.twodog.Date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.Month;

public class HutoolDateTime {
    public static void main(String[] args) {
        parse();
    }

    public static void parse() {
        DateTime dateTime = new DateTime("2017-01-05 12:34:23", DatePattern.NORM_DATETIME_FORMAT);

        //年，结果：2017
        int year = dateTime.year();

        //季度（非季节），结果：Season.SPRING
        //Season season = dateTime.seasonEnum();

        //月份，结果：Month.JANUARY
        Month month = dateTime.monthEnum();

        //日，结果：5
        int day = dateTime.dayOfMonth();

        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
    }
}
