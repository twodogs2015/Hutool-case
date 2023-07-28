package com.twodog.http;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HutoolHttpRequest {
    public static void main(String[] args) {
        httpResponse();
    }

    public static void httpRequestPost() {

        String url = "http://172.26.145.45:88/cargoexchange-open-api/cargoTracking/JD";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("awbNo", "38400412");
        paramMap.put("awbPostfix", "00000001");
        paramMap.put("awbPrefix", "784");

        String headerJson = "{\n" +
                "        \"appId\": \"jd\",\n" +
                "        \"sign\": \"A+fQg/HqRS9GZjCp0Gxc1EYoomk=\",\n" +
                "        \"uri\": \"/cargoexchange-open-api/cargoTracking/JD\",\n" +
                "        \"nonce\": \"6eb126e9-b759-40d5-9893-58848387d2fb\",\n" +
                "        \"timestamp\": \"1687768068612\"\n" +
                "    }";

        //JSONObject entries = JSONUtil.parseObj(headerJson);
        Map<String, String> headerMap = JSONUtil.toBean(headerJson, Map.class);
        headerMap.put("Content-Type", "application/json;charset=UTF-8");
        headerMap.put("authorization", "sck username=\"csair-logistics\"");

        //链式构建请求
        HttpResponse hutoolHttp = HttpRequest.post(url)
                .headerMap(headerMap, true)
                //.contentType("application/json")
                //.header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .body(JSONUtil.toJsonStr(paramMap))//表单入参内容
                //.body(paramMap.toString())//JSON入参内容
                .timeout(20000)//超时，毫秒
                .execute();

        Console.log(hutoolHttp.getStatus());
        Console.log(hutoolHttp.body());
    }

    public static void httpsRequestPost() {

        String url = "https://opentest.csair.com/csair-itc/track/cargoTracking";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("awbNo", "38400412");
        paramMap.put("awbPostfix", "00000001");
        paramMap.put("awbPrefix", "784");

        String headerJson = "{\n" +
                "        \"appId\": \"jd\",\n" +
                "        \"sign\": \"XgxZpmVU/uadHLtubU9L3sdNPek=\",\n" +
                "        \"uri\": \"/cargoexchange-open-api/cargoTracking/JD\",\n" +
                "        \"nonce\": \"e765d813-0de3-47f5-bf5d-0fecb172dc1a\",\n" +
                "        \"timestamp\": \"1687768829396\"\n" +
                "    }";

        //JSONObject entries = JSONUtil.parseObj(headerJson);
        Map<String, String> headerMap = JSONUtil.toBean(headerJson, Map.class);
        headerMap.put("Content-Type", "application/json;charset=UTF-8");
        headerMap.put("authorization", "sck username=\"csair-logistics\"");

        //System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");

        //链式构建请求
        HttpResponse hutoolHttp = HttpRequest.post(url)
                //.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.108.170.38", 9980)))
                //.setHttpProxy("10.108.170.38", 9080)
                .headerMap(headerMap, true)
//                .contentType("application/json")
                //.header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .body(JSONUtil.toJsonStr(paramMap))//表单入参内容
                //.body(paramMap.toString())//JSON入参内容
                .timeout(20000)//超时，毫秒
                .execute();

        Console.log(hutoolHttp.getStatus());
        Console.log(hutoolHttp.body());
    }

    public static void httpRequestGet() {

        String url = "http://10.79.10.55:9596/RequestHead/GetRequestHead";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appId", "shili");
        paramMap.put("appSecret", "test");
        paramMap.put("uri", "/cargoexchange-open-api/CGOExchange/SendToSaveCargoDataMsg");

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("appId", "appId");
        headerMap.put("timestamp", "timestamp");
        headerMap.put("nonce", "nonce");
        headerMap.put("sign", "updateSign");

        HttpResponse hutoolHttp = HttpRequest.get(url)
                .form(paramMap)
                .contentType("application/json")
                .headerMap(headerMap, true)
                .timeout(2000)
                .execute();


        Console.log(hutoolHttp.getStatus());
        Console.log(hutoolHttp.body());
    }

    public static void httpResponse() {
        //请求列表页
        String listContent = HttpUtil.get("https://www.oschina.net/action/ajax/get_more_news_list?newsType=&p=2");
        //使用正则获取所有标题
        List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
        for (String title : titles) {
            //打印标题
            Console.log(title);
        }

    }
}
