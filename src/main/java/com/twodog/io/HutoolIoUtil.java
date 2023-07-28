package com.twodog.io;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

import java.io.*;

public class HutoolIoUtil {
    public static void main(String[] args) {

        ioUtilsByChar();

        System.out.println("成功");

    }


    //io方法

    //IoUtil
    public static void ioUtilsBytes() {

        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = FileUtil.getInputStream("F:/111\\a.txt");

            outputStream = FileUtil.getOutputStream("F:\\111/c.txt");

            IoUtil.copy(inputStream, outputStream, IoUtil.DEFAULT_LARGE_BUFFER_SIZE);
        } finally {
            IoUtil.close(outputStream);
            IoUtil.close(inputStream);
        }
    }

    public static void ioUtilsByChar() {
        //BufferedReader reader = FileUtil.getReader("", "");
        BufferedOutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            String aa = "jwoeigjoie\r\nfijoago\r\ndsm";

            byteArrayInputStream = new ByteArrayInputStream(aa.getBytes());
            bufferedInputStream = new BufferedInputStream(byteArrayInputStream);

            outputStream = FileUtil.getOutputStream("F:/111/abcd.txt");

            IoUtil.copy(bufferedInputStream, outputStream, IoUtil.DEFAULT_BUFFER_SIZE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(outputStream);
            IoUtil.close(bufferedInputStream);
            IoUtil.close(byteArrayInputStream);
        }
    }


}
