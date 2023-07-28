package com.twodog.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.twodog.entity.ReturnSms;

import java.util.*;
import java.util.function.Supplier;

public class HutoolObjectUtil {
    public static void main(String[] args) {
        isBasicType();
    }


    public static void instant() {

        //String dateStr = "2023-06-14T08:28:45.055Z";
        String dateStr1 = "2023-06-14 08:28:45";
        String dateStr2 = "2023-06-14";

        // 此处判断如果dateStr为null，则调用`Instant.now()`，不为null则执行`DateUtil.parse`
        /*Date date = ObjectUtil.defaultIfNull(dateStr, () ->
                        DateUtil.parse(dateStr, DatePattern.NORM_DATETIME_PATTERN)
                , new Date());

        System.out.println(date);*/

        Date date1 = ObjectUtil.defaultIfNull(dateStr1, () ->
                        DateUtil.parse(dateStr1, DatePattern.NORM_DATE_PATTERN)
                , new Date());

        System.out.println(date1);

        Date date2 = ObjectUtil.defaultIfNull(dateStr2, () ->
                        DateUtil.parse(dateStr2, DatePattern.NORM_DATE_PATTERN)
                , new Date());

        System.out.println(date2);

        String aa = null;


        String s = ObjectUtil.defaultIfNull(aa, (Supplier<? extends String>) () -> StrUtil.EMPTY);
        System.out.println(s + "====>");
    }

    public static void ObjectUtilBean() {
        ReturnSms returnSms_aa = new ReturnSms("Success", "ok", "2032", "135", "test");
        ReturnSms returnSms_bb = new ReturnSms("Success", "ok", "2032", "135", "test");

        if (ObjectUtil.equal(returnSms_aa, returnSms_bb)) {
            System.out.println("相等");
        }


        Map<String, String> map = new HashMap<>();
        map.put("ii", "oo");

        int length = ObjectUtil.length(map);

        System.out.println(length);

        if (ObjectUtil.contains(map, "oo")) {
            System.out.println("包含");
        }


        System.out.println("================");
        ReturnSms returnSms1 = new ReturnSms("", "", "", "", "");
        ReturnSms returnSms3 = new ReturnSms(null, null, null, null, null);
        ReturnSms returnSms4 = new ReturnSms();
        ReturnSms returnSms2 = null;

        if (ObjectUtil.isNull(returnSms2)) {
            System.out.println("空的");
        }
        if (ObjectUtil.isNotNull(returnSms2)) {
            System.out.println("不为空");
        }
        if (BeanUtil.isNotEmpty(returnSms2)) {
            System.out.println("内容不为空");
        }
        if (BeanUtil.isEmpty(returnSms2)) {
            System.out.println("内容为空");
        }


    }

    public static void serialize() {

        ReturnSms returnSms_aa = new ReturnSms("Success", "ok", "2032", "135", "test");

        String str = StrUtil.str(ObjectUtil.serialize(returnSms_aa), CharsetUtil.CHARSET_UTF_8);
        System.out.println(str);

        ReturnSms deserialize = (ReturnSms) ObjectUtil.deserialize(str.getBytes(), ReturnSms.class);

        System.out.println(deserialize);

    }

    public static void isBasicType() {

        Boolean aa1 = false;
        Byte aa2 = 1;
        Character aa3 = 1;
        Double aa4 = 1.00;
        Float aa5 = 1.0F;
        Integer aa6 = 1;
        Long aa7 = 1L;
        Short aa8 = 1;
        boolean aa9 = true;
        byte aa10 = 1;
        char aa11 = 1;
        double aa12 = 1;
        float aa13 = 1;
        int aa14 = 1;
        long aa15 = 1;
        short aa16 = 1;

        String aa17 = "1";

        List aalist = new ArrayList();
        Collections.addAll(aalist, aa1, aa2, aa3, aa4, aa5, aa6, aa7, aa8, aa9, aa10, aa11, aa12, aa13, aa14, aa15, aa16, aa17);
        for (Object aa : aalist) {
            if (ObjectUtil.isBasicType(aa)) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
        }
    }
}

