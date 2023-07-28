package com.twodog.util;

import cn.hutool.core.util.URLUtil;

import java.net.URL;

public class HutoolURLUtil {
    public static void main(String[] args) {
        encode();
    }

    public static void url() {
        URL url = URLUtil.getURL("KeyValue.properties");
        System.out.println(url.getPath());
    }

    public static void getPath(){
        String path = URLUtil.getPath("http://172.26.145.45:88/cargoexchange-open-consumer/HandleFile/dynamicEquilibrium");
        String path2 = URLUtil.getPath("http://172.26.145.45:88/DataExchange/QueryReceivedInfo?cityCode=CAN&awbPrefix=784&awbNo=69755394&awbPostfix=00000001");
        System.out.println(path);
        System.out.println(path2);
    }

    public static void encode(){
        String aa = "5Xs*ge @ g ge- weg_ge + a.g / e。0adb.zip";

        String encode = URLUtil.encode(aa);

        System.out.println(encode);

        System.out.println(URLUtil.decode(encode));
    }
}
