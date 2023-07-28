package com.twodog.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

// 注解使用Lombok
@Data
public class UserA {
    private String name;
    private String a;
    private Date date;
    private List<KeyBean> sqs;
}
