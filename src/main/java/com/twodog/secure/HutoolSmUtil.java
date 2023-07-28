package com.twodog.secure;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.ECKeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.security.KeyPair;

/**
 * 国密算法工具
 */
public class HutoolSmUtil {
    public static void main(String[] args) {
        sm4();
    }

    public static void sm2_01() {
        String text = "我是一段测试aaaa";

        SM2 sm2 = SmUtil.sm2();
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);

        System.out.println(encryptStr);

        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println(decryptStr);
    }

    public static void sm2_02() {
        String text = "我是一段测试aaaa";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);

        System.out.println(encryptStr);

        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println(decryptStr);
    }

    public static void sm2_03() {
        String content = "我是Hanley.";
        final SM2 sm2 = SmUtil.sm2();
        String sign = sm2.signHex(HexUtil.encodeHexStr(content));
        System.out.println(sign);
        // true
        boolean verify = sm2.verifyHex(HexUtil.encodeHexStr(content), sign);
        System.out.println(verify);
    }

    public static void sm2_04() {
        String content = "我是Hanley.";
        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        final SM2 sm2 = new SM2(pair.getPrivate(), pair.getPublic());

        byte[] sign = sm2.sign(content.getBytes());

        //System.out.println(StrUtil.utf8Str(sign));

        // true
        boolean verify = sm2.verify(content.getBytes(), sign);
        System.out.println(verify);
    }

    public static void sm2_quxian_01() {
        String privateKeyHex = "FAB8BBE670FAE338C9E9382B9FB6485225C11A3ECB84C938F10F20A93B6215F0";
        String x = "9EF573019D9A03B16B0BE44FC8A5B4E8E098F56034C97B312282DD0B4810AFC3";
        String y = "CC759673ED0FC9B9DC7E6FA38F0E2B121E02654BF37EA6B63FAF2A0D6013EADF";

        // 数据和ID此处使用16进制表示
        String data = "434477813974bf58f94bcf760833c2b40f77a5fc360485b0b9ed1bd9682edb45";
        String id = "31323334353637383132333435363738";

        final SM2 sm2 = new SM2(privateKeyHex, x, y);
        // 生成的签名是64位
        sm2.usePlainEncoding();

        final String sign = sm2.signHex(data, id);
        // true
        boolean verify = sm2.verifyHex(data, sign);

        System.out.println(sign);
        System.out.println(verify);
    }

    public static void sm2_quxian_02() {
        //需要签名的明文,得到明文对应的字节数组
        byte[] dataBytes = "我是一段测试aaaa".getBytes();
        //指定的私钥
        String privateKeyHex = "1ebf8b341c695ee456fd1a41b82645724bc25d79935437d30e7e4b0a554baa5e";

        // 此构造从5.5.9开始可使用
        final SM2 sm2 = new SM2(privateKeyHex, null, null);
        sm2.usePlainEncoding();
        byte[] sign = sm2.sign(dataBytes, null);
    }

    public static void sm2_quxian_03() {
        //指定的公钥
        String publicKeyHex = "04db9629dd33ba568e9507add5df6587a0998361a03d3321948b448c653c2c1b7056434884ab6f3d1c529501f166a336e86f045cea10dffe58aa82ea13d725363";
        //需要加密的明文,得到明文对应的字节数组
        byte[] dataBytes = "我是一段测试aaaa".getBytes();
        //签名值
        String signHex = "2881346e038d2ed706ccdd025f2b1dafa7377d5cf090134b98756fafe084dddbcdba0ab00b5348ed48025195af3f1dda29e819bb66aa9d4d088050ff148482a";

        final SM2 sm2 = new SM2(null, ECKeyUtil.toSm2PublicParams(publicKeyHex));
        sm2.usePlainEncoding();

        // true
        boolean verify = sm2.verify(dataBytes, HexUtil.decodeHex(signHex));
        System.out.println(verify);
    }

    public static void sm3() {
        //结果为：136ce3c86e4ed909b76082055a61586af20b4dab674732ebd4b599eef080c9be
        String digestHex = SmUtil.sm3("aaaaa");
        System.out.println(digestHex);
    }

    public static void sm4() {
        String content = "test中文";
        SymmetricCrypto sm4 = SmUtil.sm4();

        String encryptHex = sm4.encryptHex(content);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);

        System.out.println(encryptHex);
        System.out.println(decryptStr);
    }
}
