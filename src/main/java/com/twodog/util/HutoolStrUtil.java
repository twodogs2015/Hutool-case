package com.twodog.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;

public class HutoolStrUtil {

    public static void main(String[] args) {
        constant();
    }

    public static void stringUtil() {
        String aa = "";
        String bb = null;
        String cc = "       ";
        String dd = "    \\   ";
        String ee = "   123456   ";

        if (StrUtil.hasBlank(aa)) {
            System.out.println("aa======>");
        }
        if (StrUtil.hasBlank(bb)) {
            System.out.println("bb======>");
        }
        if (StrUtil.hasBlank(cc)) {
            System.out.println("cc======>");
        }
        if (StrUtil.hasBlank(dd)) {
            System.out.println("dd======>");
        }
        if (StrUtil.hasBlank(ee)) {
            System.out.println("ee======>");
        }


        if (StrUtil.hasEmpty(aa)) {
            System.out.println("aa======>");
        }
        if (StrUtil.hasEmpty(bb)) {
            System.out.println("bb======>");
        }
        if (StrUtil.hasEmpty(cc)) {
            System.out.println("cc======>");
        }
        if (StrUtil.hasEmpty(dd)) {
            System.out.println("dd======>");
        }
        if (StrUtil.hasEmpty(ee)) {
            System.out.println("ee======>");
        }

        /*
        aa======>
        bb======>
        cc======>

        aa======>
        bb======>
        */
    }

    public static void removeSuffix() {
        //去除前后缀
        String aa = "adb.jpg";
        String bb = "20adb.pNg";
        String cc = "30adb.tXt";
        String dd = "5Xs0adb.zip";

        //去除后缀
        String suffix = StrUtil.removeSuffix(aa, ".jpg");
        System.out.println(suffix);

        System.out.println(StrUtil.removePrefix(bb, "20"));

        //忽略大小写去除后缀
        System.out.println(StrUtil.removeSuffixIgnoreCase(cc, ".txt"));

        System.out.println(StrUtil.removePrefixIgnoreCase(dd, "5xs0"));

    }

    public static void sub() {
        String aa = "adb.jpg";
        String bb = "20adb.pNg";
        String cc = "30adb.tXt";
        String dd = "5Xs0adb.zip";

        String s = StrUtil.subSuf(aa, 1);
        System.out.println(s);

        System.out.println(StrUtil.sub(bb, 3, -1));

        System.out.println(StrUtil.subAfter(cc, "b.", false));


        System.out.println(StrUtil.subBefore(dd, "a", false));
    }

    public static void strBytes() {
        String aa = "adb.jpg";
        String bb = "20adb.pNg";
        String cc = "30adb.tXt";
        String dd = "5Xs0adb.zip";

        byte[] bytes = StrUtil.bytes(aa, CharsetUtil.CHARSET_UTF_8);

        System.out.println(StrUtil.str(bytes, CharsetUtil.CHARSET_UTF_8));

    }

    public static void formater() {
        String dd = "5Xs0{}ad{}b.zip";
        System.out.println(StrUtil.format(dd, "123", "900", "234"));
    }

    public static void constant() {
        System.out.println("11" + StrUtil.CRLF + "22");

        System.out.println(StrUtil.NULL);
        System.out.println(StrUtil.EMPTY);

        System.out.println(StrUtil.DOT);


    }
}
