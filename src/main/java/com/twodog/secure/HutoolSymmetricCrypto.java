package com.twodog.secure;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.nio.charset.StandardCharsets;

/**
 * 对称加密
 */
public class HutoolSymmetricCrypto {
    public static void main(String[] args) {
        aes_03();
        des_03();
    }

    public static void aes_01() {
        String content = "test中文";

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);

        //加密为16进制表示/基于64编码
        String encryptHex = aes.encryptBase64(content);
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);

        System.out.println(encryptHex);
        System.out.println(decryptStr);
    }

    public static void des_01() {
        String content = "test中文";

        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();

        SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DESede, key);

        //加密
        byte[] encrypt = des.encrypt(content);
        //解密
        byte[] decrypt = des.decrypt(encrypt);

        //加密为16进制字符串（Hex表示）
        String encryptHex = des.encryptHex(content);
        //解密为字符串
        String decryptStr = des.decryptStr(encryptHex);

        System.out.println(encryptHex);
        System.out.println(decryptStr);
    }

    public static void aes_02() {
        String content = "test中文";

        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

        // 构建
        AES aes = SecureUtil.aes(key);

        // 加密
        byte[] encrypt = aes.encrypt(content);
        // 解密
        byte[] decrypt = aes.decrypt(encrypt);

        // 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);

        System.out.println(encryptHex);
        System.out.println(decryptStr);
    }

    //自定义内置模式和偏移
    public static void aes_03() {
        AES aes = new AES(Mode.CTS
                , Padding.PKCS5Padding
                , "16或24或32个字节128/192/256".getBytes(StandardCharsets.UTF_8)
                , "必须 16 字节".getBytes(StandardCharsets.UTF_8));
        String encryptBase64 = aes.encryptBase64("wehgqigij384713");

        String decryptStr = aes.decryptStr(encryptBase64);

        System.out.println(encryptBase64);
        System.out.println(decryptStr);

    }

    //PKCS7Padding模式
    public static void aes_04() {
        AES aes = new AES("CBC", "PKCS7Padding",
                // 密钥，可以自定义
                "0123456789ABHAEQ".getBytes(),
                // iv加盐，按照实际需求添加
                "DYgjCEIMVrj2W9xN".getBytes());

        // 加密为16进制表示
        String encryptHex = aes.encryptHex("content");
        System.out.println(encryptHex);

        // 解密
        String decryptStr = aes.decryptStr(encryptHex);
        System.out.println(decryptStr);
    }

    //快速构建
    public static void des_02(){
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
        DES des = SecureUtil.des(key);

    }

    //自定义模式和偏移
    public static void des_03(){
        DES des = new DES(Mode.CTS
                , Padding.PKCS5Padding
                , "0CoJUm6Qyw8W8jud".getBytes()
                , "01020304".getBytes());

        String encryptBase64 = des.encryptBase64("test1234");

        String decryptStr = des.decryptStr(encryptBase64);

        System.out.println(encryptBase64);
        System.out.println(decryptStr);
    }
}
