package com.twodog.util;

import cn.hutool.core.util.XmlUtil;
import com.twodog.entity.ReturnSms;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathConstants;
import java.util.List;

public class HutoolXmlUtil {
    public static void main(String[] args) {
        xmlUtilToBean();
    }

    public static String aa = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "\n" +
            "<returnsms> \n" +
            "  <returnstatus>Success（成功）</returnstatus>  \n" +
            "  <message>ok</message>  \n" +
            "  <remainpoint>1490</remainpoint>  \n" +
            "  <taskID>885</taskID>  \n" +
            "  <successCounts>1</successCounts> \n" +
            "</returnsms>";

    public static void xmlUtil() {

        String amp = XmlUtil.AMP;


        Document document = XmlUtil.parseXml(aa);
        Document document1 = XmlUtil.readXML("F:\\111\\test.xml");


        Object byXPath = XmlUtil.getByXPath("//returnsms", document, XPathConstants.STRING);
        System.out.println(byXPath);


        Element elementByXPath = XmlUtil.getElementByXPath("//returnsms", document);

        List<Element> elementList = XmlUtil.getElements(elementByXPath, "");

        for (Element element : elementList) {
            System.out.println(element.getTagName());
        }


    }

    public static void xmlUtilToStr() {

        String s = XmlUtil.toStr(XmlUtil.parseXml(aa));
        System.out.println(s);

        XmlUtil.toFile(XmlUtil.parseXml(aa), "F:\\111\\test22.xml");

    }

    /**
     * XML与对象转换
     *          writeObjectAsXml
     *          readObjectFromXml
     *          禁用以上两个，可以将xml转为bean，再通过toFile到文件，从xml文件读转为bean，也是如此
     */
    public static void xmlUtilToBean() {

        //xml to bean
        Document document = XmlUtil.parseXml(aa);
        Node returnsms = XmlUtil.getNodeByXPath("returnsms", document);

        ReturnSms returnSms = XmlUtil.xmlToBean(returnsms, ReturnSms.class);

        System.out.println(returnSms);

        //bean to xml to file
        ReturnSms returnSms2 = new ReturnSms("Success", "ok", "2032", "135", "test");

        Document document1 = XmlUtil.beanToXml(returnSms2);

        //XmlUtil.writeObjectAsXml(new File("F:\\111\\test33.xml"), returnSms2);
        XmlUtil.toFile(document1, "F:\\\\111\\\\test33.xml");

        String s = XmlUtil.toStr(document1);
        System.out.println(s);


    }
}
