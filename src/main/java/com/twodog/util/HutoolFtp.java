package com.twodog.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;

public class HutoolFtp {
    public static void main(String[] args) throws Exception {
        Sftp();
        System.out.println("成功！");
    }

    public static void upload() throws Exception {
        Ftp ftp = null;
        try {
            //匿名登录（无需帐号密码的FTP服务器）
            //ftp = new Ftp("10.7%9.10.12#8", 222, "tang____user", "t@U#159___++753");
            ftp = new Ftp("", 0, "**", "***");

            //切换为主动模式
            ftp.setMode(FtpMode.Active);

            //进入远程目录
            //ftp.cd("/exchangelogZIP/127.0.0.1");

            //上传本地文件
            ftp.upload("/exchangelogZIP/127.0.0.1", "2023-06-16.zip", FileUtil.file("F:\\111\\2023-06-16.zip"));

            //下载远程文件
            //ftp.download("/exchangelogZIP/127.0.0.1", "2023-06-16.zip", FileUtil.file("F:\\111\\getway2023-06-16.zip"));

            //关闭连接
            IoUtil.close(ftp);
        } finally {
            if (ftp != null) {
                ftp.close();
            }
        }
    }

    public static void Sftp() {
        Sftp sftp = null;
        try {
            sftp = JschUtil.createSftp("172.0.0.1", 22, "root", "123456");
            //进入远程目录
            sftp.cd("/exchangelog/127.0.0.1");
            //上传本地文件
            sftp.put("F:\\111\\2023-06-16.zip", "/exchangelog/127.0.0.1");
            //下载远程文件
            sftp.get("/exchangelog/127.0.0.1/2023-05-28.zip", "F:\\111\\2023-05-28.zip");
        } finally {
            //关闭连接
            if (sftp != null) {
                sftp.close();
            }
        }


    }
}
