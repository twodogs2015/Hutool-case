package com.twodog.util;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class HutoolIdUtil {
    public static void main(String[] args) {
        simpleUUID();
        objectId();
        getSnowflake();
    }

    public static void Snowflake() {
        System.out.println(System.currentTimeMillis());

        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id1 = snowflake.nextId();
        long id4 = snowflake.nextId();
        long id5 = snowflake.nextId();

        //简单使用
        long id2 = IdUtil.getSnowflakeNextId();
        String id3 = IdUtil.getSnowflakeNextIdStr();

        System.out.println(id1);
        System.out.println(id2);
        System.out.println(id3);
        System.out.println(id4);
        System.out.println(id5);
        System.out.println(System.currentTimeMillis());
    }

    public static void simpleUUID() {
        System.out.println(IdUtil.randomUUID());
        System.out.println(IdUtil.simpleUUID());
    }

    public static void objectId() {
        String s = IdUtil.objectId();
        String next = ObjectId.next();
        System.out.println(s);
        System.out.println(next);
    }

    public static void getSnowflake() {
        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id1 = snowflake.nextId();
        long id4 = snowflake.nextId();

        //简单使用
        long id2 = IdUtil.getSnowflakeNextId();
        String id3 = IdUtil.getSnowflakeNextIdStr();
        System.out.println(id1);
        System.out.println(id4);
        System.out.println(id2);
        System.out.println(id3);

    }
}
