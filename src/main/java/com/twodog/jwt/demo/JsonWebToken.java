package com.twodog.jwt.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.net.util.Base64;

import javax.crypto.Mac;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

@Slf4j
public class JsonWebToken {

    private static final String KEY = "00000000111111112222222233333333";

    private static final String DOT = ".";

    private static final Map<String, String> HEADERS = new HashMap<>(8);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        HEADERS.put("alg", "HS256");
        HEADERS.put("typ", "JWT");
    }

    String generateHeaderPart() throws JsonProcessingException {
        byte[] headerBytes = OBJECT_MAPPER.writeValueAsBytes(HEADERS);
        String headerPart = new String(Base64.encodeBase64(headerBytes, false, true), StandardCharsets.US_ASCII);
        log.info("生成的Header部分为:{}", headerPart);
        return headerPart;
    }

    String generatePayloadPart(Map<String, Object> claims) throws JsonProcessingException {
        byte[] payloadBytes = OBJECT_MAPPER.writeValueAsBytes(claims);
        String payloadPart = new String(Base64.encodeBase64(payloadBytes, false, true), StandardCharsets.UTF_8);
        log.info("生成的Payload部分为:{}", payloadPart);
        return payloadPart;
    }

    String generateSignaturePart(String headerPart, String payloadPart) {
        String content = headerPart + DOT + payloadPart;
        Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, KEY.getBytes(StandardCharsets.UTF_8));
        byte[] output = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
        String signaturePart = new String(Base64.encodeBase64(output, false, true), StandardCharsets.UTF_8);
        log.info("生成的Signature部分为:{}", signaturePart);
        return signaturePart;
    }

    public String generate(Map<String, Object> claims) throws Exception {
        String headerPart = generateHeaderPart();
        String payloadPart = generatePayloadPart(claims);
        String signaturePart = generateSignaturePart(headerPart, payloadPart);
        String jws = headerPart + DOT + payloadPart + DOT + signaturePart;
        log.info("生成的JWT为:{}", jws);
        return jws;
    }

    /*public static void main(String[] args) throws Exception {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put("iss", "throwx");
        claims.put("jid", 10087L);
        claims.put("exp", 1688524838993L);
        JsonWebToken jsonWebToken = new JsonWebToken();
        System.out.println("自行生成的JWT:" + jsonWebToken.generate(claims));
    }*/


    public Map<Part, PartContent> parse(String jwt) throws Exception {
        System.out.println("当前解析的JWT:" + jwt);
        Map<Part, PartContent> result = new HashMap<>(8);
        // 这里暂且认为所有的输入JWT的格式都是合法的
        StringTokenizer tokenizer = new StringTokenizer(jwt, DOT);
        String[] jwtParts = new String[3];
        int idx = 0;
        while (tokenizer.hasMoreElements()) {
            jwtParts[idx] = tokenizer.nextToken();
            idx++;
        }
        String headerPart = jwtParts[0];
        PartContent headerContent = new PartContent();
        headerContent.setRawContent(headerPart);
        headerContent.setPart(Part.HEADER);
        headerPart = new String(Base64.decodeBase64(headerPart), StandardCharsets.UTF_8);
        headerContent.setPairs(OBJECT_MAPPER.readValue(headerPart, new TypeReference<Map<String, Object>>() {
        }));
        result.put(Part.HEADER, headerContent);
        String payloadPart = jwtParts[1];
        PartContent payloadContent = new PartContent();
        payloadContent.setRawContent(payloadPart);
        payloadContent.setPart(Part.PAYLOAD);
        payloadPart = new String(Base64.decodeBase64(payloadPart), StandardCharsets.UTF_8);
        payloadContent.setPairs(OBJECT_MAPPER.readValue(payloadPart, new TypeReference<Map<String, Object>>() {
        }));
        result.put(Part.PAYLOAD, payloadContent);
        String signaturePart = jwtParts[2];
        PartContent signatureContent = new PartContent();
        signatureContent.setRawContent(signaturePart);
        signatureContent.setPart(Part.SIGNATURE);
        result.put(Part.SIGNATURE, signatureContent);
        return result;
    }

    public void verify(String jwt) throws Exception {
        System.out.println("当前校验的JWT:" + jwt);
        Map<Part, PartContent> parseResult = parse(jwt);
        PartContent headerContent = parseResult.get(Part.HEADER);
        PartContent payloadContent = parseResult.get(Part.PAYLOAD);
        PartContent signatureContent = parseResult.get(Part.SIGNATURE);
        String signature = generateSignaturePart(headerContent.getRawContent(), payloadContent.getRawContent());
        if (!Objects.equals(signature, signatureContent.getRawContent())) {
            throw new IllegalStateException("签名校验异常");
        }
        String iss = payloadContent.getPairs().get("iss").toString();
        // iss校验
        if (!Objects.equals(iss, "throwx")) {
            throw new IllegalStateException("ISS校验异常");
        }
        long exp = Long.parseLong(payloadContent.getPairs().get("exp").toString());
        // exp校验,有效期14天
        if (System.currentTimeMillis() - exp > 24 * 3600 * 1000 * 14) {
            throw new IllegalStateException("exp校验异常,JWT已经过期");
        }
        // 省略其他校验项
        System.out.println("JWT校验通过");
    }


    public static void main(String[] args) throws Exception {
        JsonWebToken jsonWebToken = new JsonWebToken();
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0aHJvd3giLCJqaWQiOjEwMDg3LCJleHAiOjE2ODg1MjQ4Mzg5OTN9.T9QeSQq34CxmLsqjARaTq8pVX4C6u2Equ5tiTgCKYjc";
        Map<Part, PartContent> parseResult = jsonWebToken.parse(jwt);
        System.out.printf("解析结果如下:\nHEADER:%s\nPAYLOAD:%s\nSIGNATURE:%s%n",
                parseResult.get(Part.HEADER),
                parseResult.get(Part.PAYLOAD),
                parseResult.get(Part.SIGNATURE)
        );
        System.out.println();
        System.out.println();
        System.out.println();
        jsonWebToken.verify(jwt);
    }
}
