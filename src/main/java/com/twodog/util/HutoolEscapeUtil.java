package com.twodog.util;

import cn.hutool.core.util.EscapeUtil;

public class HutoolEscapeUtil {
    public static void main(String[] args) {
        escape();
    }

    public static void escape() {
        String aa = "5Xs*ge @ g ge- weg_ge + a.g / e。0adb.zip";

        String escape = EscapeUtil.escape(aa);

        System.out.println(escape);

        System.out.println(EscapeUtil.unescape(escape));

    }
}
