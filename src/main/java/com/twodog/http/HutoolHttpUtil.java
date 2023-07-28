package com.twodog.http;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HutoolHttpUtil {
    public static void main(String[] args) {
        encodeParams();
    }

    public static void get() {
        String s = HttpUtil.get("http://10.79.10.55:9596/RequestHead/GetRequestHead?appId=shili&appSecret=test&uri=/cargoexchange-open-api/CGOExchange/SendToSaveCargoDataMsg");
        System.out.println(s);
    }

    public static void post() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("awbNo", "01369104");
        paramMap.put("awbPostfix", "00000001");
        paramMap.put("awbPrefix", "784");
        paramMap.put("operation", "ins");
        String s = HttpUtil.post(
                "http://10.79.10.55:9596/TangApiRequestHead/GetRequestHead?appId=BYWL&appSecret=V3tHmoWPvYIQKOdH&uri=/tangapi2java/BYWL/FreightDepot"
                , paramMap);
        System.out.println(s);
    }

    public static void encodeParams() {
        String param = "http://10.79.10.55:9596/TangApiRequestHead/GetRequestHead?appId=BYWL&appSecret=V3tHmoWPvYIQKOdH&uri=/tangapi2java/BYWL/FreightDepot";
        String encodeParams = HttpUtil.encodeParams(param, CharsetUtil.CHARSET_UTF_8);
        System.out.println(encodeParams);

        Map<String, List<String>> stringListMap = HttpUtil.decodeParams(param, CharsetUtil.UTF_8);
        System.out.println(stringListMap);

        Map<String, Object> map = new HashMap<>();
        map.put("appId", "bywl");
        map.put("appSecret", "V3tHmoWPvYIQKOdH");

        String toParams = HttpUtil.toParams(map);
        System.out.println(toParams);

        String urlWithForm = HttpUtil.urlWithForm("http://10.79.10.55:9596/TangApiRequestHead/GetRequestHead", map, CharsetUtil.CHARSET_UTF_8, true);
        System.out.println(urlWithForm);

        String mimeType = HttpUtil.getMimeType("F:\\111\\line.png");
        System.out.println(mimeType);//image/png

    }
}
