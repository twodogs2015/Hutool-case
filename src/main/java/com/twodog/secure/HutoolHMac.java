package com.twodog.secure;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class HutoolHMac {
    public static void main(String[] args) throws Exception {
        HmacSHA1();
    }

    private static void HmacMD5() {
        String testStr = "test";

        // 此处密钥如果有非ASCII字符，考虑编码
        byte[] key = "password".getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);

        // b977f4b13f93f549e06140971bded384
        String macHex1 = mac.digestHex(testStr);

        System.out.println(macHex1);

        String encode = Base64.encode(macHex1);
        System.out.println(encode);
        System.out.println(StrUtil.utf8Str(Base64.decode(encode)));
    }

    private static void HmacSHA1() throws Exception {
        String data = "jd|/cargoexchange-open-api/cargoTracking/JD|1688030313828|3ac138ec-3a2e-4a93-8db0-b74bbae57525";

        SecretKey secretKey = SecureUtil.generateKey("HmacSHA1", "jingdong2022".getBytes());
        Mac mac = SecureUtil.createMac("HmacSHA1");

        mac.init(secretKey);
        byte[] bytes = mac.doFinal(data.getBytes());

        String encode = Base64.encode(bytes);
        System.out.println(encode);
    }
}
