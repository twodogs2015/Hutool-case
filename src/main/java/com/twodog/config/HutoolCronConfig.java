package com.twodog.config;

import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Configuration
public class HutoolCronConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //动态的添加定时任务每5秒执行一次
        CronUtil.schedule("*/2 * * * * *", new Task() {
            @Override
            public void execute() {
                Console.log("动态定时任务，每2秒执行一次");
            }
        });

        //支持秒定时
        CronUtil.setMatchSecond(true);

        //开启定时
        CronUtil.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("=======MyServletContextListener--contextDestroyed");


    }
}
