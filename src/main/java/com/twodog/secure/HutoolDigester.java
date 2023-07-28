package com.twodog.secure;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;

/**
 * 摘要算法加密
 */
public class HutoolDigester {
    public static void main(String[] args) {
        md5();
        sm3();
    }

    public static void md5(){
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        // 32e3f38e0012b78faf9b7d1adb34cb48
        String digestHex = md5.digestHex("testStr");

        System.out.println(digestHex);

        // 32e3f38e0012b78faf9b7d1adb34cb48
        String md5Hex1 = DigestUtil.md5Hex("testStr");
        System.out.println(md5Hex1);
    }

    public static void sm3(){
        Digester digester = DigestUtil.digester("sm3");

        //136ce3c86e4ed909b76082055a61586af20b4dab674732ebd4b599eef080c9be
        String digestHex = digester.digestHex("testStr");

        System.out.println(digestHex);
    }
}
