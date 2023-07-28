package com.twodog.io;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class HutoolResourceUtil {
    public static void main(String[] args) {
        ResourceUtil();

        System.out.println("成功");
    }


    public static void ResourceUtil() {
        BufferedReader utf8Reader = null;
        Properties properties = null;
        try {
            utf8Reader = ResourceUtil.getReader("KeyValue.properties", CharsetUtil.CHARSET_UTF_8);
            properties = new Properties();
            properties.load(utf8Reader);
            Set<String> propertyNames = properties.stringPropertyNames();
            for (String propertyName : propertyNames) {
                System.out.println(propertyName + "===>" + properties.getProperty(propertyName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(utf8Reader);
            properties.clear();
        }

        System.out.println("======================");

        //获取文件内容
        String readUtf8Str = ResourceUtil.readUtf8Str("KeyValue.properties");

        System.out.println(readUtf8Str);

        //全路径
        System.out.println(ResourceUtil.getResource("KeyValue.properties"));

        ClassPathResource classPathResource = new ClassPathResource("KeyValue.properties");

        URL url = classPathResource.getUrl();
        System.out.println(url.getPath());
    }

    public static void ClassPathResource() {
        BufferedReader utf8Reader = null;
        Properties properties = null;
        InputStream inputStream = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("KeyValue.properties");

            inputStream = HutoolResourceUtil.class.getClassLoader().getResourceAsStream("KeyValue.properties");


            utf8Reader = classPathResource.getReader(CharsetUtil.CHARSET_UTF_8);

            properties = new Properties();
            properties.load(inputStream);
            Set<String> propertyNames = properties.stringPropertyNames();
            for (String propertyName : propertyNames) {
                System.out.println(propertyName + "===>" + properties.getProperty(propertyName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(utf8Reader);
            IoUtil.close(inputStream);
            if (null != properties) {
                properties.clear();
            }
        }
    }
}
