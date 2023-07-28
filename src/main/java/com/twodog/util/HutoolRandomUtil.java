package com.twodog.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Set;

public class HutoolRandomUtil {
    public static void main(String[] args) {
        randomEleSet();
    }

    public static void randomInt() {
        for (int i = 0; i < 30; i++) {
            int num = RandomUtil.randomInt(1, 10);//[1,10)
            System.out.println(num);
        }
    }

    public static void randomNumbers() {
        String s = RandomUtil.randomNumbers(8);
        System.out.println(s);

        String s1 = RandomUtil.randomString(5);
        System.out.println(s1);

    }

    public static void randomEleSet() {

        Set<Integer> integers = RandomUtil.randomEleSet(CollUtil.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9), 9);
        System.out.println(integers);
    }
}
