package com.twodog.http;

import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;

public class HutoolSimpleServer {
    public static void main(String[] args) {
        //getFilePath();
        //json();
        //form();
        uploadFile();
    }

    public static void createServer() {
        HttpUtil.createServer(8888)
                .addAction("/", (req, res) -> {
                    res.write("Hello Hutool Server");
                })
                .start();
    }

    public static void getFilePath() {
        HttpUtil.createServer(8888)
                // 设置默认根目录
                .setRoot("D:\\soft\\nginx-1.22.1\\html")
                .start();

    }

    public static void json() {
        HttpUtil.createServer(8888)
                // 返回JSON数据测试
                .addAction("/restTest", (request, response) ->
                        response.write("{\"id\": 1, \"msg\": \"OK\"}", ContentType.JSON.toString())
                ).start();
    }

    public static void form() {
        HttpUtil.createServer(8888)
                // http://localhost:8888/formTest?a=1&a=2&b=3
                .addAction("/formTest", (request, response) ->
                        response.write(request.getParams().toString(), ContentType.TEXT_PLAIN.toString())
                ).start();
    }

    public static void uploadFile() {
        HttpUtil.createServer(8888)
                .addAction("/file", (request, response) -> {
                            final UploadFile file = request.getMultipart().getFile("file");
                            // 传入目录，默认读取HTTP头中的文件名然后创建文件
                            file.write("F:\\111\\abc.txt");
                            response.write("OK!", ContentType.TEXT_PLAIN.toString());
                        }
                )
                .start();
    }
}
