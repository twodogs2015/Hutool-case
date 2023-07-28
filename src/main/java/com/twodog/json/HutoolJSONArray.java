package com.twodog.json;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.twodog.entity.KeyBean;
import com.twodog.entity.User;

import java.util.ArrayList;
import java.util.List;

public class HutoolJSONArray {
    public static void main(String[] args) {
        jsonArray();
        parseBean();
        parseStr();
        parseBeanList();
        parseBeanArray();
        parseMapList();
        getByPath();
    }

    public static void jsonArray() {
        //方法1
        JSONArray array = JSONUtil.createArray();
        //方法2
        //JSONArray array = new JSONArray();

        array.add("value1");
        array.add("value2");
        array.add("value3");

        //转为JSONArray字符串
        String toString = array.toString();
        System.out.println(toString);

    }

    public static void parseBean() {
        KeyBean b1 = new KeyBean();
        b1.setAkey("aValue1");
        b1.setBkey("bValue1");
        KeyBean b2 = new KeyBean();
        b2.setAkey("aValue2");
        b2.setBkey("bValue2");

        ArrayList<KeyBean> list = CollUtil.newArrayList(b1, b2);

        // [{"akey":"aValue1","bkey":"bValue1"},{"akey":"aValue2","bkey":"bValue2"}]
        JSONArray jsonArray = JSONUtil.parseArray(list);
        Console.log(jsonArray);

        // aValue1
        String akey = jsonArray.getJSONObject(0).getStr("akey");
        Console.log(akey);

    }

    public static void parseStr() {
        String jsonStr = "[\"value1\", \"value2\", \"value3\"]";
        JSONArray array = JSONUtil.parseArray(jsonStr);

        System.out.println(array);
    }

    public static void parseBeanList() {
        String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
        JSONArray array = JSONUtil.parseArray(jsonArr);

        List<User> userList = JSONUtil.toList(array, User.class);

        // 111
        Integer id = userList.get(0).getId();

        System.out.println(id);
    }

    public static void parseBeanArray() {
        String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
        JSONArray array = JSONUtil.parseArray(jsonArr);

        User[] list = array.toArray(new User[0]);

        System.out.println(list.length);

        Integer id = list[0].getId();

        System.out.println(id);
    }

    public static void parseMapList() {
        String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
        JSONArray array = JSONUtil.parseArray(jsonArr);

        List<Dict> list = JSONUtil.toList(array, Dict.class);

        System.out.println(list.get(0));
        // 111
        Integer id = list.get(0).getInt("id");

        System.out.println(id);
    }

    public static void getByPath() {
        String jsonStr = "[{\"id\": \"1\",\"name\": \"a\"},{\"id\": \"2\",\"name\": \"b\"}]";
        final JSONArray jsonArray = JSONUtil.parseArray(jsonStr);

        // b
        Object byPath = jsonArray.getByPath("[1].name");

        System.out.println(byPath);

    }
}
