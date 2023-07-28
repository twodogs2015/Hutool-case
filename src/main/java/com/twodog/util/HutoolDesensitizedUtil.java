package com.twodog.util;

import cn.hutool.core.util.DesensitizedUtil;

public class HutoolDesensitizedUtil {
    public static void main(String[] args) {
        idCardNum();
    }

    public static void idCardNum() {
        String idCardNum = DesensitizedUtil.idCardNum("123456789012345678", 3, 2);
        System.out.println(idCardNum);//123*************78

        String mobilePhone = DesensitizedUtil.mobilePhone("13534349898");
        System.out.println(mobilePhone);//135****9898

        String password = DesensitizedUtil.password("abcd1234");
        System.out.println(password);//********

        String address = DesensitizedUtil.address("广东省广州市白云区云桂花园26号", 13);
        System.out.println(address);//广东省*************

        String carLicense = DesensitizedUtil.carLicense("湘T·MI784");
        System.out.println(carLicense);//湘T·****4

        String chineseName = DesensitizedUtil.chineseName("李二狗图");
        System.out.println(chineseName);//李***

        String email = DesensitizedUtil.email("ergou@gzasiainfo.com");
        System.out.println(email);//e****@gzasiainfo.com

        String fixedPhone = DesensitizedUtil.fixedPhone("784-1234567");
        System.out.println(fixedPhone);//784-*****67
    }
}
