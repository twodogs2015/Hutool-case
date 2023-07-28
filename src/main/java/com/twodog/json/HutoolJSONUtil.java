package com.twodog.json;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.twodog.entity.Price;
import com.twodog.entity.Test;

import java.util.SortedMap;
import java.util.TreeMap;

public class HutoolJSONUtil {
    public static void main(String[] args) {
        JSONUtil();
        strParse();
        xmlParse();
        jsonToXml();
        toBean();
        beanToJson();
    }

    public static void JSONUtil() {
        SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("attributes", "a");
                put("b", "b");
                put("c", "c");
            }
        };
        String toJsonStr = JSONUtil.toJsonStr(sortedMap);
        String toJsonPrettyStr = JSONUtil.toJsonPrettyStr(sortedMap);

        Console.log(toJsonStr);
        Console.log(toJsonPrettyStr);//美化后的
    }

    public static void strParse() {
        String html = "{\"name\":\"Something must have been changed since you leave\"}";
        JSONObject jsonObject = JSONUtil.parseObj(html);
        String name = jsonObject.getStr("name");
        System.out.println(name);
    }

    public static void xmlParse() {
        String s = "<sfzh>123</sfzh><sfz>456</sfz><name>aa</name><gender>1</gender>";
        JSONObject json = JSONUtil.parseFromXml(s);

        json.get("sfzh");
        json.get("name");

        System.out.println(json);
    }

    public static void jsonToXml() {
        final JSONObject put = JSONUtil.createObj()
                .set("aaa", "你好")
                .set("键2", "test");

        // <aaa>你好</aaa><键2>test</键2>
        final String s = JSONUtil.toXmlStr(put);

        String s1 = put.toString();

        System.out.println(s);
        System.out.println(s1);

    }

    public static void toBean() {
        String json = "{\"ADT\":[[{\"BookingCode\":[\"N\",\"N\"]}]]}";

        Price price = JSONUtil.toBean(json, Price.class);

        String s = price.getADT().get(0).get(0).getBookingCode().get(0);

        System.out.println(price);
        System.out.println(s);

    }

    public static void beanToJson() {
        Test test = new Test();
        test.setName("handy");
        test.setSex("男");
        // 结果: {"name":"handy","aliasSex":"男"}
        String json = JSONUtil.toJsonStr(test);
        System.out.println(json);
    }

}
