package com.twodog.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;

public class HutoolIdcardUtil {
    public static void main(String[] args) {
        isValidCard();
    }

    public static void isValidCard() {
        String ID_18 = "321083197812162119";
        String ID_15 = "150102880730303";

        //是否有效
        boolean valid = IdcardUtil.isValidCard(ID_18);
        boolean valid15 = IdcardUtil.isValidCard(ID_15);
        System.out.println(valid);
        System.out.println(valid15);

        //转换
        String convert15To18 = IdcardUtil.convert15To18(ID_15);
        System.out.println(convert15To18);

        //年龄
        DateTime date = DateUtil.parse("2017-04-10");

        int age = IdcardUtil.getAgeByIdCard(ID_18, date);
        System.out.println(age);

        int age2 = IdcardUtil.getAgeByIdCard(ID_15, date);
        System.out.println(age2);

        //生日
        String birth = IdcardUtil.getBirthByIdCard(ID_18);
        System.out.println(birth);

        String birth2 = IdcardUtil.getBirthByIdCard(ID_15);
        System.out.println(birth2);

        //省份
        String province = IdcardUtil.getProvinceByIdCard(ID_18);
        System.out.println(province);

        String province2 = IdcardUtil.getProvinceByIdCard(ID_15);
        System.out.println(province2);

        //性别
        int genderByIdCard = IdcardUtil.getGenderByIdCard(ID_15);
        System.out.println(genderByIdCard);

        int byIdCard = IdcardUtil.getGenderByIdCard(ID_18);
        System.out.println(byIdCard);

    }
}
