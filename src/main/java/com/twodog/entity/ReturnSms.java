package com.twodog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnSms {

    private String returnstatus = "7";
    private String message;
    private String remainpoint;
    private String taskID;
    private String successCounts;

}
