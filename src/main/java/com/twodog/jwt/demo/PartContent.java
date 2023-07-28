package com.twodog.jwt.demo;

import lombok.Data;

import java.util.Map;

@Data
public class PartContent {

    private Part part;

    private String rawContent;

    private Map<String, Object> pairs;
}
