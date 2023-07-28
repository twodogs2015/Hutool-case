package com.twodog.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;

public class HutoolZipUtil {
    public static void main(String[] args) throws Exception {
        gzip();
        System.out.println("成功！");
    }

    public static void zip1() {
        String getFilePath = "F:\\111\\aaaa";

        String saveFilePath = "F:\\111\\aaaa.zip";


        ZipUtil.zip(getFilePath, saveFilePath);
    }

    public static void zip2() {
        String getFilePath = "F:\\111\\b.txt";

        String saveFilePath = "F:\\111\\bb.zip";


        ZipUtil.zip(getFilePath, saveFilePath, true);
    }

    public static void zip3() {
        String getFilePath = "F:\\111\\b.txt";

        String saveFilePath = "F:\\111\\bb.zip";


        ZipUtil.zip(getFilePath, saveFilePath, false);
    }

    public static void zip4() {
        String saveFilePath = "F:\\111\\bb.zip";

        String getFilePath1 = "F:\\111\\a.txt";
        String getFilePath2 = "F:\\111\\b.txt";
        String getFilePath3 = "F:\\111\\c.txt";

        ZipUtil.zip(FileUtil.file(saveFilePath), false,
                FileUtil.file(getFilePath1),
                FileUtil.file(getFilePath2),
                FileUtil.file(getFilePath3)
        );
    }

    public static void unZip() {

        String getFilePath = "F:\\111\\aaaa.zip";

        String saveFilePath = "F:\\111\\cccc\\";


        ZipUtil.unzip(getFilePath, saveFilePath);
    }


    public static void gzip() throws Exception {
        byte[] fjsdis = ZipUtil.gzip("fjsdi", "UTF-8");

        for (byte fjsdi : fjsdis) {
            System.out.print(fjsdi+"==>");
        }

        System.out.println("================");

        byte[] bytes = ZipUtil.unGzip(fjsdis);
        for (byte fjsdi : bytes) {
            System.out.print(fjsdi+"==>");
        }

        System.out.println("================");
        String utf8 = new String(bytes, "utf8");
        System.out.println(utf8);
    }
}
