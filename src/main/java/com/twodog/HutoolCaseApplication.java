package com.twodog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HutoolCaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(HutoolCaseApplication.class, args);
        System.out.println("启动成功");
    }
}
