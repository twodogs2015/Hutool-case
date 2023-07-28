package com.twodog.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringframeworkCron {

    @Scheduled(cron = "${HutoolCron.TenSeconds}")
    public void scheduled(){
        System.out.println("-------------Scheduled  10 miao------");
    }
}
