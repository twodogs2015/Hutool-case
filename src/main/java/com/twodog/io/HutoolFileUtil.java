package com.twodog.io;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.Date;

public class HutoolFileUtil {

    public static void main(String[] args) {
        fileUtil();

        System.out.println("成功");
    }


    public static void fileUtil() {
//        FileUtil.mkdir("");
        FileUtil.copy("F:/111/c.txt", "F:/111/d.txt", true);
        File[] ls = FileUtil.ls("F:/111/");

        for (File file : ls) {
            String type = FileUtil.getType(file);
            Date date = FileUtil.lastModifiedTime(file);
            String format = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println(type + "===>" + FileUtil.getName(file) + "========>" + format);
        }
        boolean del = FileUtil.del("F:/111/新建文件夹/");
        System.out.println(del);


    }
}
