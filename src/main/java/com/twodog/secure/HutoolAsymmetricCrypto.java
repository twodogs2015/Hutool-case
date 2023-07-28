package com.twodog.secure;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.ECIES;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

public class HutoolAsymmetricCrypto {
    public static void main(String[] args) throws Exception {
        rsa_01();
        rsa_case_02();
        /*String s = rsa_01();
        rsa_02(s);*/
    }

    public static RSA rsa = new RSA();

    public static String rsa_01() {

        //获得私钥
        rsa.getPrivateKey();
        rsa.getPrivateKeyBase64();
        //获得公钥
        rsa.getPublicKey();
        rsa.getPublicKeyBase64();

        AsymmetricCrypto init = rsa.init("rsa", rsa.getPrivateKey(), rsa.getPublicKey());

        byte[] encrypt1 = init.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt1 = init.decrypt(encrypt1, KeyType.PrivateKey);

        System.out.println(Base64.encode(encrypt1));
        System.out.println(Base64.encode(decrypt1));

        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        //解密
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

        System.out.println(Base64.encode(encrypt));
        System.out.println(Base64.encode(decrypt));
        System.out.println(StrUtil.utf8Str(decrypt));

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

        System.out.println(Base64.encode(encrypt2));
        System.out.println(Base64.encode(decrypt2));

        System.out.println(StrUtil.utf8Str(decrypt2));
        return Base64.encode(encrypt2);
    }

    public static void rsa_02(String s) {

        //私钥加密，公钥解密
        byte[] encrypt2 = Base64.decode(s);

        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

        System.out.println(Base64.encode(encrypt2));
        System.out.println(Base64.encode(decrypt2));

        System.out.println(StrUtil.utf8Str(decrypt2));
    }

    public static void keyPair() throws Exception {
        //KeyPair pair = SecureUtil.generateKeyPair("RSA");
        //PublicKey aPublic = pair.getPublic();
        //PrivateKey aPrivate = pair.getPrivate();

        PublicKey publicKey = rsa.getPublicKey();
        PrivateKey privateKey = rsa.getPrivateKey();

        //String encodeaPrivate = Base64.encode(aPrivate.getEncoded());
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        //String encodeaPublic = Base64.encode(aPublic.getEncoded());
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        System.out.println(privateKeyBase64);
        System.out.println(publicKeyBase64);

        Cipher cipher = rsa.getCipher();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = "source".getBytes();

        /**
         * 执行加密操作
         */
        String encode = Base64.encode(cipher.doFinal(bytes));
        System.out.println(encode);

        /**
         * 执行解密操作
         */
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodeBytes = Base64.decode(encode);

        String decode = StrUtil.utf8Str(cipher.doFinal(decodeBytes));
        System.out.println(decode);
    }

    public static void rsa_case() {
        String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIL7pbQ+5KKGYRhw7jE31hmA"
                + "f8Q60ybd+xZuRmuO5kOFBRqXGxKTQ9TfQI+aMW+0lw/kibKzaD/EKV91107xE384qOy6IcuBfaR5lv39OcoqNZ"
                + "5l+Dah5ABGnVkBP9fKOFhPgghBknTRo0/rZFGI6Q1UHXb+4atP++LNFlDymJcPAgMBAAECgYBammGb1alndta"
                + "xBmTtLLdveoBmp14p04D8mhkiC33iFKBcLUvvxGg2Vpuc+cbagyu/NZG+R/WDrlgEDUp6861M5BeFN0L9O4hz"
                + "GAEn8xyTE96f8sh4VlRmBOvVdwZqRO+ilkOM96+KL88A9RKdp8V2tna7TM6oI3LHDyf/JBoXaQJBAMcVN7fKlYP"
                + "Skzfh/yZzW2fmC0ZNg/qaW8Oa/wfDxlWjgnS0p/EKWZ8BxjR/d199L3i/KMaGdfpaWbYZLvYENqUCQQCobjsuCW"
                + "nlZhcWajjzpsSuy8/bICVEpUax1fUZ58Mq69CQXfaZemD9Ar4omzuEAAs2/uee3kt3AvCBaeq05NyjAkBme8SwB0iK"
                + "kLcaeGuJlq7CQIkjSrobIqUEf+CzVZPe+AorG+isS+Cw2w/2bHu+G0p5xSYvdH59P0+ZT0N+f9LFAkA6v3Ae56OrI"
                + "wfMhrJksfeKbIaMjNLS9b8JynIaXg9iCiyOHmgkMl5gAbPoH/ULXqSKwzBw5mJ2GW1gBlyaSfV3AkA/RJC+adIjsRGg"
                + "JOkiRjSmPpGv3FOhl9fsBPjupZBEIuoMWOC8GXK/73DHxwmfNmN7C9+sIi4RBcjEeQ5F5FHZ";

        RSA rsa = new RSA(PRIVATE_KEY, null);

        String a = "2707F9FD4288CEF302C972058712F24A5F3EC62C5A14AD2FC59DAB93503AA0FA17113A020EE4EA35EB53F"
                + "75F36564BA1DABAA20F3B90FD39315C30E68FE8A1803B36C29029B23EB612C06ACF3A34BE815074F5EB5AA3A"
                + "C0C8832EC42DA725B4E1C38EF4EA1B85904F8B10B2D62EA782B813229F9090E6F7394E42E6F44494BB8";

        byte[] aByte = HexUtil.decodeHex(a);
        byte[] decrypt = rsa.decrypt(aByte, KeyType.PrivateKey);

        System.out.println(StrUtil.utf8Str(decrypt));
        //Junit单元测试
        //Assert.assertEquals("虎头闯杭州,多抬头看天,切勿只管种地", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

    }

    public static void ecies() {
        final ECIES ecies = new ECIES();
        String textBase = "我是一段特别长的测试";

        // 公钥加密，私钥解密
        String encryptStr = ecies.encryptBase64(textBase, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(ecies.decrypt(encryptStr, KeyType.PrivateKey));

        System.out.println(encryptStr);
        System.out.println(decryptStr);
    }

    public static void rsa_case_02() {
        String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCvK/vRR5Y/TxVYSBV2WxTD/id0DG73X9sExREUjwNnB8BJsY772CGVJwv7uCp3p/x7IVGggDyLqv0btyP/nZTOad7FnhtfplhH2wkEfGLjvZGv7zSrX/32C79vHr14Eh/Sn9KMWDyI1McLtnP9V1YtTlY3D28JJWawi1BR6JPZ+44Mltmcn/zFc5TPx/5qM2n/YPdjbo1A1OsvK8SVQaiyn4Z3ejGnZtg2AbH5JXRPqCqyTe9uH8QFSlQUbotf8khMd0YgERDqwtfrNyt10h0mwr6Xtmfoq6r71+YRuqCEfuSJaRfkvLlYLXZgGP5j/2RIKZ/2Scmfu+UgLy3PG5szAgMBAAECggEAEIgdHtYbtuPWxtses2nLHugYfxpBSrVQLTYP85m/n3Sa0Bmivd/a44Go3tu1nk9LQ6+UTYpsrF25v2jcS95qUcSqEopmWPOQ908TncnDZ2zXl/wCQ34usI7RRUJobTGAsVcF/trA88To+BEDB1C7wFDZUB7zwrA17cloDiLd+T9dOiGusqO9K/3C192uZX1bKZ1sNipo1US3BG/dsF1oqHWNpmt7GIBZsfs430hsI02HdjZeSQH8zeEH+rc3D8X84tw4PqxzCOslWx9umJzEnlosHzL6PBkQ+SR3rpU0/BF1Tj3wPtHNKfUuEiTwBFTf0KVD2tTVC7k5ixL5HweS8QKBgQDnkkkNoSPYrx8HQcZC2Fx5uCm7yhDY02SjMLKt+ppHTojUg8qdeQlGsMTNJm3j4uQnQIfC0IgmXDcP+RN5iZyaovJZSvVlTSfP8nMLfVXKSsFlvBY8ldXomC+DNRMSgw8RgmGIjztboAETTGP3W+NZoEj0Q4zOiO/YDXCQYLem6wKBgQDBpphuTWuCgpGxidiUaECfcZ3jab3FqMUUFYhF/JRhmneLVfD+7YeSVaRq3axfDrLtztOwOMD5A8TBEzwznMItRnetA0Vnbiol4svq/TBnbOwC6nsQv25c7sC7JUyjRNfyfyJxivrZCBWJzhaJZoaTGT4mqk64wi8LxxpUASHa2QKBgCBm1ZX0oCFnj33idC1SPM5DOIOwKF9OZNx4qeeyEvg7QuYJcsZJ80RS45aY8/NZAfeEDfryVrqXWJUBkvoEiUcR2bsow+FixFlSYUqQRPrDLwBfKvVbOc0LzkobmQmUEP2wMpl7ASE26C+dFre7wbn+tXaIJSGZA4ZFiySIg+n1AoGAd2tkLwlhVk9sx15piEOuLmVjZ52K4PzN5C/PJNG5M2ONewiZoKwcYbqctSZUqR4oa4yTcOXe4vFXAXX6Nx3hhQgivzf4Met25HwzIovWsMEz3ix5jf9vcLHverlqxHis7ydFt11sijB5D81eiocGUGpNsIxwFXHyj0HOU6WLGikCgYBMTP6lOpAkMTQgrGzSVwN4tVVhIimD/g93Kxo5JqCNTGyRVTLweCMKONaj8i8TIsiPckin/ucs+nc9HOMhA56Pt6RRmnn4IFxEcq9w1o1vvztYtdkXg2pLpE87h3lCtsZNxlJ2YalNvPA436kboxwXONDpQIIo2oG1yZ2jNOVG8A==";

        String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAryv70UeWP08VWEgVdlsUw/4ndAxu91/bBMURFI8DZwfASbGO+9ghlScL+7gqd6f8eyFRoIA8i6r9G7cj/52UzmnexZ4bX6ZYR9sJBHxi472Rr+80q1/99gu/bx69eBIf0p/SjFg8iNTHC7Zz/VdWLU5WNw9vCSVmsItQUeiT2fuODJbZnJ/8xXOUz8f+ajNp/2D3Y26NQNTrLyvElUGosp+Gd3oxp2bYNgGx+SV0T6gqsk3vbh/EBUpUFG6LX/JITHdGIBEQ6sLX6zcrddIdJsK+l7Zn6Kuq+9fmEbqghH7kiWkX5Ly5WC12YBj+Y/9kSCmf9knJn7vlIC8tzxubMwIDAQAB";


        RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);

        //加密
        byte[] encrypt = rsa.encrypt("test".getBytes(), KeyType.PublicKey);
        String encode = Base64.encode(encrypt);
        System.out.println(encode);


        //解密
        byte[] aByte = Base64.decode(encode);
        byte[] decrypt = rsa.decrypt(aByte, KeyType.PrivateKey);
        String decode = StrUtil.utf8Str(decrypt);
        System.out.println(decode);
    }
}
