package com.twodog.util;

import cn.hutool.core.util.ReflectUtil;
import com.twodog.entity.ReturnSms;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具
 */
public class HutoolReflectUtil {
    public static void main(String[] args) throws Exception {
        newInstance();
    }

    /*
        获取构造方法
        获取字段
        获取字段值
        获取方法
        执行方法（对象方法和静态方法）
    */

    public static void newInstance() throws Exception {
        //获取构造器(无参/满参)
        ReturnSms returnSms = ReflectUtil.newInstance(ReturnSms.class, "11", "11", "11", "11", "11");

        //执行方法
        ReflectUtil.invoke(returnSms, "setMessage", "message");
        ReflectUtil.invoke(returnSms, "setReturnstatus", "0");
        System.out.println(returnSms);

        Method[] methods = ReflectUtil.getMethods(returnSms.getClass());
        for (Method method : methods) {
            System.out.println(method.getName());
            if (method.getName().equals("getReturnstatus")) {
                Object invoke = method.invoke(returnSms);
                System.out.println(invoke + "==========");
            }
            if (method.getName().equals("setReturnstatus")) {
                Object invoke = method.invoke(returnSms, "34");
                System.out.println(invoke + "==========");
            }
        }
        System.out.println("================");

        Field[] fields = ReflectUtil.getFields(returnSms.getClass());
        for (Field field : fields) {
            System.out.println(field.getName()
                    + "默认值===>" + ReflectUtil.getFieldValue(ReflectUtil.newInstance(ReturnSms.class), field)
                    + "赋值后===>" + ReflectUtil.getFieldValue(returnSms, field));
        }
    }

}
