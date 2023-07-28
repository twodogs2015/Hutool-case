package com.twodog.Date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

public class HutoolTimeInterval {
    public static void main(String[] args) {
        start();
        groupStart();
    }

    public static void start() {
        TimeInterval timer = DateUtil.timer();

        //---------------------------------
        //-------这是执行过程
        //---------------------------------
        ThreadUtil.sleep(60100);


        long l1 = timer.intervalMinute();//花费分钟数
        long l = timer.intervalRestart();//返回花费时间毫秒，并重置开始时间
        long interval = timer.interval();//花费毫秒数
        System.out.println(l1);
        System.out.println(l);
        System.out.println(interval);

    }

    public static void groupStart() {
        final TimeInterval timer = new TimeInterval();

        // 分组1
        timer.start("1");
        ThreadUtil.sleep(800);

        // 分组2
        timer.start("2");
        ThreadUtil.sleep(900);

        Console.log("Timer 1 took {} ms", timer.intervalMs("1"));
        Console.log("Timer 2 took {} ms", timer.intervalMs("2"));

    }
}
