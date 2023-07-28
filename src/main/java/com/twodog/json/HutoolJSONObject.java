package com.twodog.json;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.twodog.entity.KeyBean;
import com.twodog.entity.UserA;

import java.util.Date;

public class HutoolJSONObject {
    public static void main(String[] args) {
        parseToBean();
    }

    public static void parseObj() {
        JSONObject jsonObject = JSONUtil.createObj();
        JSONObject entries = new JSONObject();

        String jsonStr = "{\"b\":\"value2\",\"c\":\"value3\",\"a\":\"value1\"}";

        JSONObject parseObj = JSONUtil.parseObj(jsonStr);
        parseObj.putOnce("d", "value4");
        System.out.println(parseObj.toStringPretty());
    }

    public static void parseToBean() {
        UserA userA = new UserA();
        userA.setName("nameTest");
        userA.setDate(new Date());
        userA.setSqs(CollectionUtil.newArrayList(new KeyBean("a", "b"), new KeyBean("c", "")));

        // 第二个参数false表示不跳过空值，第三个参数代表排序
        JSONObject json = JSONUtil.parseObj(userA, false, false);
        json.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        Console.log(json.toStringPretty());
    }
}
