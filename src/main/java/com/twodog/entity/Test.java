package com.twodog.entity;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

@Data
public class Test {
    private String name;

    @Alias("aliasSex")
    private String sex;

}