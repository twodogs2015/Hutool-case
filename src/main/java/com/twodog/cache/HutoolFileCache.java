package com.twodog.cache;

import cn.hutool.cache.file.LFUFileCache;
import cn.hutool.cache.file.LRUFileCache;
import cn.hutool.core.thread.ThreadUtil;

public class HutoolFileCache {
    public static void main(String[] args) {
        lfuFileCache();
    }

    public static void lfuFileCache() {
        //参数1：容量，能容纳的byte数
        //参数2：最大文件大小，byte数，决定能缓存至少多少文件，大于这个值不被缓存直接读取
        //参数3：超时。毫秒
        LFUFileCache cache = new LFUFileCache(1000, 500, 2000);
        //byte[] bytes = cache.getFileBytes("F:/111/line.png");

        System.out.println(cache.getFileBytes("F:/111/line.png").length);

        ThreadUtil.sleep(2000);
        System.out.println(cache.getFileBytes("F:/111/line.png").length);


    }

    public static void lruFileCache() {
        //参数1：容量，能容纳的byte数
        //参数2：最大文件大小，byte数，决定能缓存至少多少文件，大于这个值不被缓存直接读取
        //参数3：超时。毫秒
        LRUFileCache cache = new LRUFileCache(1000, 500, 2000);
        //byte[] bytes = cache.getFileBytes("F:/111/line.png");

        System.out.println(cache.getFileBytes("F:/111/line.png").length);

        ThreadUtil.sleep(2000);
        System.out.println(cache.getFileBytes("F:/111/line.png").length);

    }
}
