package com.twodog.http;

import cn.hutool.http.HtmlUtil;

public class HutoolHtmlUtil {
    public static void main(String[] args) {
        filter();
    }

    public static void escape() {
        String html = "<html><body>123'123'</body></html>";
        // 结果为：&lt;html&gt;&lt;body&gt;123&#039;123&#039;&lt;/body&gt;&lt;/html&gt;
        String escape = HtmlUtil.escape(html);
        System.out.println(html);
        System.out.println(escape);

        //还原
        String unescape = HtmlUtil.unescape(escape);
        System.out.println(unescape);
    }

    public static void removeHtmlTag() {
        String str = "pre<img src=\"xxx/dfdsfds/test.jpg\">";
        // 结果为：pre
        String result = HtmlUtil.removeHtmlTag(str, "img");
        System.out.println(result);


    }

    public static void cleanHtmlTag() {
        String str = "pre<div class=\"test_div\">\r\n\t\tdfdsfdsfdsf\r\n</div><div class=\"test_div\">BBBB</div>";
        // 结果为：pre\r\n\t\tdfdsfdsfdsf\r\nBBBB
        String result = HtmlUtil.cleanHtmlTag(str);
        System.out.println(result);
    }

    public static void unwrapHtmlTag() {
        String str = "pre<div class=\"test_div\">abc</div>";
        // 结果为：preabc
        String result = HtmlUtil.unwrapHtmlTag(str, "div");
        System.out.println(result);
    }

    public static void removeHtmlAttr() {
        String html = "<div class=\"test_div\"></div><span class=\"test_div\"></span>";
        // 结果为：<div></div><span></span>
        String result = HtmlUtil.removeHtmlAttr(html, "class");
        System.out.println(result);
    }

    public static void removeAllHtmlAttr() {
        String html = "<div class=\"test_div\" width=\"120\"></div>";
        // 结果为：<div></div>
        String result = HtmlUtil.removeAllHtmlAttr(html, "div");
        System.out.println(result);
    }

    public static void filter(){
        String html = "<alert>243</alert>";
        // 结果为："243"
        String filter = HtmlUtil.filter(html);
        System.out.println(filter);


        String html2 = "<input type=\"button\" onclick='go_to_url(\"${myUrl}\");' />";
        html2 = HtmlUtil.escape(html2);
        String filter2 = HtmlUtil.filter(html2);
        System.out.println(filter2);
    }
}

