package com.twodog.http;

import cn.hutool.core.lang.Console;
import cn.hutool.http.webservice.SoapClient;

import javax.xml.soap.SOAPElement;

public class HutoolSoapClient {
    public static void main(String[] args) throws Exception {
        soapClients();
    }

    //使用SoapUI (opens new window)解析WSDL地址，找到WebService方法和参数。
    public static final String soapParam = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <web:getCountryCityByIp>\n" +
            "         <!--Optional:-->\n" +
            "         <web:theIpAddress>?</web:theIpAddress>\n" +
            "      </web:getCountryCityByIp>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>\n";

    /**
     * 按照SoapUI中的相应内容构建SOAP请求。
     * 方法名为：web:getCountryCityByIp
     * 参数只有一个，为:web:theIpAddress
     * 定义了一个命名空间，前缀为web，URI为http://WebXml.com.cn/
     */
    public static void soapClient() {
        // 新建客户端
        SoapClient client = SoapClient.create("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx")
                // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
                .setMethod("web:getCountryCityByIp", "http://WebXml.com.cn/")
                // 设置参数，此处自动添加方法的前缀：web
                .setParam("theIpAddress", "218.21.240.106");

        // 发送请求，参数true表示返回一个格式化后的XML内容
        // 返回内容为XML字符串，可以配合XmlUtil解析这个响应
        Console.log(client.send(true));
        System.out.println();
        Console.log(client.getMsgStr(true));//调用SoapClient对象的getMsgStr方法可以查看生成的XML，以检查是否与SoapUI生成的一致。
    }

    //对于请求体是列表参数或多参数的情况，如：这类请求可以借助addChildElement完成。
    public static final String soapParams = "<web:method>\n" +
            "  <arg0>\n" +
            "    <fd1>aaa</fd1>\n" +
            "    <fd2>bbb</fd2>\n" +
            "  </arg0>\n" +
            "</web:method>\n";

    public static void soapClients() throws Exception {
        SoapClient client = SoapClient.create("https://hutool.cn/WebServices/test.asmx")
                .setMethod("web:method", "http://hutool.cn/");

        SOAPElement arg0 = client.getMethodEle().addChildElement("arg0");
        arg0.addChildElement("fdSource").setValue("?");
        arg0.addChildElement("fdTemplated").setValue("?");

        Console.log(client.send(true));
        System.out.println();
        Console.log(client.getMsgStr(true));
    }

    //详细的问题解答见：https://gitee.com/dromara/hutool/issues/I4QL1V
}
